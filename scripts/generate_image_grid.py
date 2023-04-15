import os
from pathlib import Path
import re
import shutil

from PIL import Image

def get_image_categories(image_directory):
    """
    Get the image categories in the given directory, ignoring folders starting with "_".
    :param image_directory: The directory containing the image categories.
    :return: A list of image category names.
    """
    return [
        category
        for category in os.listdir(image_directory)
        if os.path.isdir(os.path.join(image_directory, category)) and not category.startswith("_")
    ]

def generate_image_grid(image_directory):
    """
    Generate an image grid for the given image directory.
    :param image_directory: The directory containing the image categories.
    :return: A list of strings, each representing a line in the image grid.
    """
    categories = get_image_categories(image_directory)
    output = []

    for category in categories:
        type_folder = image_directory / category
        output.append(f"## {type_folder.name}\n")
        output.append("| Light | Dark |\n")
        output.append("| --- | --- |\n")

        light_images = sorted((type_folder / "light").glob("*.png"))
        dark_images = sorted((type_folder / "dark").glob("*.png"))

        for light_image, dark_image in zip(light_images, dark_images):
            output.append(f"| ![Light]({light_image}) | ![Dark]({dark_image}) |\n")

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
    destination = root_folder / ".." / "docs" / "res" / "README.md"
    shutil.copy2(source, destination)

if __name__ == "__main__":
    root_folder = Path("")
    image_grids = "".join(generate_image_grid(root_folder))
    update_readme(root_folder, image_grids)
    copy_readme_to_docs_res(root_folder)
