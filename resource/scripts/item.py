import os
import datetime
import argparse

from os.path import exists
from path import ItemsInitPath
from path import LangPath
from path import modId
from path import ItemsPath


def main(name, description):
	assert exists(ItemsInitPath)==True
	assert exists(LangPath)==True

	itemClassName = name.capitalize() + "_item"

	if len(description) < 1:
		description = name[name.find('_') + 1:].capitalize().replace('_', ' ')

	langTag = ",\n  \"item." + modId + "." + name + "\": \"" + description + "\"\n}\n"

	index = 0
	while index < len(itemClassName):
		if itemClassName[index] == '_':
			itemClassName = itemClassName[:index] + itemClassName[index + 1].upper() + itemClassName[index + 2:]
		index += 1

	with open(ItemsInitPath, "a+") as file:
		file.seek(0, os.SEEK_END)
		pos = file.tell() - 1
		while pos > 0 and file.read(1) != "}":
			pos -= 1
			file.seek(pos, os.SEEK_SET)
		if pos > 0:
			file.seek(pos - 1, os.SEEK_SET)
			file.truncate()
			file.write("\tpublic static final RegistryObject<Item> " +\
				name.upper() + " = REGISTRY.register(\"" + name + "\", " + itemClassName + "::new);\n}\n")

	with open(LangPath, "a+") as file:
		file.seek(0, os.SEEK_END)
		pos = file.tell() - 1
		while pos > 0 and file.read(1) != "}":
			pos -= 1
			file.seek(pos, os.SEEK_SET)
		if pos > 0:
			file.seek(pos - 1, os.SEEK_SET)
			file.truncate()
			file.write(langTag)

	if ~exists(ItemsPath + itemClassName + ".java"):
		file = open(ItemsPath + itemClassName + ".java", "a")
		file.close()


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--n", help="name of item",
                        type=str, required=True)
    parser.add_argument("--d", help="batch size used for training",
                        type=str, default="")
    # parser.add_argument("--lr", help="learning rate for training",
    #                     type=float, default=1e-2)
    # parser.add_argument("--percent", help="percent of training data to use for validation",
    #                     type=float, default=0.8)
    # parser.add_argument("--input", help="input file",
    #                     type=str, required=True)
    # parser.add_argument("--logs_dir", help="logs directory",
    #                     type=str, default="")
    args = parser.parse_args()

    main(args.n, args.d)

    # if not os.path.isdir(args.logs_dir):
    #     os.makedirs(args.logs_dir)