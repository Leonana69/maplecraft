import os
import datetime
import argparse
import cv2
import numpy as np

from os.path import exists
from path import ItemsInitPath
from path import LangPath
from path import modId
from path import ItemsPath


# origin = [[14, 200], [33, 200], [32, 200], [33, 200], [32, 200], [33, 200], [36, 200], [33, 200]] # thunder bolt
# origin = [[39, 36], [48, 32], [55, 49], [42, 43], [31, 34]] # magic claw
# origin = [[52, 41], [60, 57]] # slash blast
# origin = [[34, 37], [75, 58]] # power strike
# origin = [[-9, 127], [22, 149], [22, 149], [31, 29], [31, 29], [31, 29]] # cold beam
# origin = [[22, 21], [23, 21], [27, 24], [26, 22]] # lucky seven
# origin = [[37, 34], [28, 64], [11, 83]] # double stab
# origin = [[5, 49], [17, 61], [19, 63], [16, 62], [13, 60], [4, 47]] # double shot
# origin = [[42, 41], [60, 71]] # arrow blow

# origin = [[67, 61], [60, 56], [83, 72], [99, 85], [119, 96], [79, 96], [96, 88], [119, 119], [129, 138]] # arrow bomb
# origin = [[37, 41], [53, 50], [36, 38]] # strafe
# origin = [[35, 110], [40, 72], [38, 35]] # arrow rain
# origin = [[22, 18], [14, 23], [14, 35]] # savage blow
# origin = [[36, 62], [37, 66], [38, 66], [38, 62], [38, 42], [35, 23]] # drain
# origin = [[57, 57], [58, 59], [68, 68]] # holy arrow
# origin = [[27, 26], [26, 45], [29, 50], [24, 41], [24, 33]] # fire arrow
# origin = [[26, -4], [27, 9], [28, 24], [28, 23], [28, 23], [28, 27], [28, 25]] # heal
# origin = [[42, 129], [40, 223], [68, 48], [82, 66], [84, 88], [83, 83], [88, 83], [86, 86]] # dragon fury

# origin = [[16, 15], [21, 52], [27, 39], [21, 36]] # iron arrow
# origin = [[19, 18], [33, 27], [36, 31], [53, 53], [64, 66], [64, 66]] # poison brace
# origin = [[53, 68], [66, 118], [92, 115], [92, 92], [85, 63]] # panic
# origin = [[29, 34], [31, 31]] # shout
# origin = [[24, 72], [22, 70], [9, 62], [3, 56]] # spear crusher
origin = [[31, 32], [46, 46], [53, 52]] # dragon roar

def main(name, count):
	imgs = []
	leftWidth = 0
	rightWidth = 0
	topHeight = 0
	bottomHeight = 0
	for i in range(0, count):
		img = cv2.imread(name + "_hit_" + str(i) + ".png", cv2.IMREAD_UNCHANGED)
		imgs.append(img)

		if img.shape[1] - origin[i][0] > rightWidth:
			rightWidth = img.shape[1] - origin[i][0]
		if img.shape[0] - origin[i][1] > bottomHeight:
			bottomHeight = img.shape[0] - origin[i][1]
		if origin[i][0] > leftWidth:
			leftWidth = origin[i][0]
		if origin[i][1] > topHeight:
			topHeight = origin[i][1]

	totalWidth = leftWidth + rightWidth
	totalHeight = topHeight + bottomHeight

	print(totalWidth, totalHeight)

	emptyImg = np.full((totalHeight, totalWidth * count, 4), (0, 0, 0, 0), dtype=np.uint8)

	for i in range(0, count):
		yOffset = topHeight - origin[i][1]
		xOffset = leftWidth - origin[i][0] + i * totalWidth
		emptyImg[yOffset : yOffset + imgs[i].shape[0], xOffset : xOffset + imgs[i].shape[1]] = imgs[i]
		print(imgs[i].shape[0], imgs[i].shape[1])
	cv2.imwrite(name + "_hit.png", emptyImg)
	return
	

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--i", help="input file name",
                        type=str, required=True)
    parser.add_argument("--n", help="number of files",
                        type=int, required=True)

    args = parser.parse_args()

    main(args.i, args.n)