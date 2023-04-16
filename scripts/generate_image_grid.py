import os
import logging
from pathlib import Path
from pathlib import Path
import re
import subprocess
import shutil

from PIL import Image

def get_image_categories(image_directory):
    """
    Get the image categories in the given directory, ignoring folders starting with "_".
    :param image_directory: The directory containing the image categories.
    :return: A list of image category names.
    """
    categories = []
    for folder in sorted(image_directory.glob("*")):
        if folder.is_dir() and not folder.name.startswith((".", "_")):
            categories.append(folder.name)
    return categories

def generate_image_grid(image_directory):
    """
    Generate an image grid for the given image directory.
    :param image_directory: The directory containing the image categories.
    :return: A list of strings, each representing a line in the image grid.
    """
    categories = get_image_categories(image_directory)
    logging.info(f"Found {len(categories)} image categories: {categories}")
    output = []

    for category in categories:
        type_folder = image_directory / category

        categoryName = category.replace('_', '-').title()

        logging.info(f"Processing category {categoryName} from {type_folder}")
        output.append(f"<h2>{categoryName}</h2>\n")
        output.append('<a href="https://maxkeppeler.github.io/sheets-compose-dialogs/api/'+category+'/index.html" title="Module Documentation">Module Documentation</a>\n</br>\n')
        output.append("<table style=\"border: none;\">\n")
        output.append("<tr>\n")
        output.append("<th style=\"text-align: center;\">Light</th>\n")
        output.append("<th style=\"text-align: center;\">Dark</th>\n")
        output.append("<th style=\"text-align: center;\"></th>\n")
        output.append("<th style=\"text-align: center;\"></th>\n")
        output.append("</tr>\n")

        lightImagePath = type_folder / "light"
        darkImagePath = type_folder / "dark"

        light_images = sorted(lightImagePath.glob("*.png"))
        dark_images = sorted(darkImagePath.glob("*.png"))

        logging.info(f"Found {len(light_images)} light images and {len(dark_images)} dark images")
        for light_image, dark_image in zip(light_images, dark_images):
            name = light_image.stem.replace('_', ' ').title()
            codeFile = light_image.stem.replace('_', ' ').title().replace(' ', '')
            output.append("<tr>\n")
            output.append(f'<td width="25%"> <p style=\"text-align: center;\">{name} <a href="https://github.com/maxkeppeler/sheets-compose-dialogs/blob/main/app/src/main/java/com/mk/sheets/compose/samples/{codeFile}.kt" title="Code">Code</a></p> <img src="{lightImagePath / light_image.name}" /></td>\n')
            output.append(f'<td width="25%"> <p style=\"text-align: center;\">{name} <a href="https://github.com/maxkeppeler/sheets-compose-dialogs/blob/main/app/src/main/java/com/mk/sheets/compose/samples/{codeFile}.kt" title="Code">Code</a></p> <img src="{darkImagePath / dark_image.name}" /></td>\n')
            output.append(f'<td width="25%"> </td>\n')
            output.append(f'<td width="25%"> </td>\n')
            output.append("</tr>\n")

        output.append("</table>\n</br>\n\n\n")

    return output

def update_readme(root_folder: Path, image_grids: str):
    """
    Update the README.md file with the given image grids.
    :param root_folder: The root folder containing the README.md file.
    :param image_grids: The image grids to include in the README.md file.
    """
    with open(root_folder / "README.md", "r", encoding="utf-8") as file:
        content = file.read()

    content = re.sub(
        r"<!-- AUTO-GENERATED-SAMPLES-CONTENT:START -->.*<!-- AUTO-GENERATED-SAMPLES-CONTENT:END -->",
        f"<!-- AUTO-GENERATED-SAMPLES-CONTENT:START -->\n{image_grids}<!-- AUTO-GENERATED-SAMPLES-CONTENT:END -->",
        content,
        flags=re.DOTALL,
    )

    with open(root_folder / "README.md", "w", encoding="utf-8") as file:
        file.write(content)

def copy_readme_to_docs_res(root_folder: Path):
    """
    Copy the README.md file from the root folder to the docs/res directory.
    :param root_folder: The root folder containing the README.md file.
    """
    source = root_folder / "README.md"
    destination = root_folder / "docs" / "README.md"
    with source.open() as f:
        readme_text = f.read()
    readme_text = readme_text.replace("docs/res", "res")
    with destination.open("w") as f:
        f.write(readme_text)

if __name__ == "__main__":
    root_folder = Path("")
    image_folder = root_folder / "docs" / "res" / "sheets"
    image_grids = "".join(generate_image_grid(image_folder))
    update_readme(root_folder, image_grids)
    copy_readme_to_docs_res(root_folder)
