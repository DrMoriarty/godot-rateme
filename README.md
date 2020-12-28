# Godot plugin for asking user to rate your app (iOS/Android)

## Installation using NativeLib addon

1. Add [NativeLib Addon](https://github.com/DrMoriarty/nativelib) into your project.
2. Find `RATEME` in plugins list and press "Install" button.
3. Enable **Custom Build** for Android use.

## Installation using CLI utility

1. Install [NativeLib-CLI](https://github.com/DrMoriarty/nativelib-cli) to your computer.
2. Make `nativelib -i rateme` in your project directory.
3. Enable **Custom Build** for Android use.

## Usage

The plugin wrapper will be automatically added into your autoloading list. Use global `rateme` class anywhere in your code.

## API

### show()

Call OS to show your user native rate dialog. 

Attention: call this method only in appropriate circumstances, after your user have enough experience to rate your app correctly.
