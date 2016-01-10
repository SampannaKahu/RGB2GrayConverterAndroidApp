# RGB2GrayConverterAndroidApp

## Brief description
A simple android app to capture color images from the camera and convert them to grayscale.

## Methodology
The image captured from the camera is iterated over pixel-by-pixel. The three components (i.e. Red, Green and Blue) are added in a weighted fashion to get the greyscale value.
The calculation formula is:
```
int grayScaleValue  = (int) (red*0.299 + green*0.587 + blue*0.114);
```

## How to use:
### Step 1: Launch the app.
![screenshot_2016-01-11-01-35-50_com projects sampannakahu rgb2grayconverter](https://cloud.githubusercontent.com/assets/10060176/12223741/f6299d12-b804-11e5-8b46-327c9ad02125.png)

### Step 2: Tap the 'CAPTURE' button to capture a photo using the camera.
![screenshot_2016-01-11-01-37-09_com android camera](https://cloud.githubusercontent.com/assets/10060176/12223744/f62a8a1a-b804-11e5-8146-8b01ba04cf7d.png)

### Step 3: Confirm your photo.
![screenshot_2016-01-11-01-37-34_com projects sampannakahu rgb2grayconverter](https://cloud.githubusercontent.com/assets/10060176/12223743/f62a798a-b804-11e5-90d3-7161eabbfeae.png)

### Step 3: Tap the 'CONVERT' button to convert the image to Grayscale.
![screenshot_2016-01-11-01-37-42_com projects sampannakahu rgb2grayconverter](https://cloud.githubusercontent.com/assets/10060176/12223742/f629c24c-b804-11e5-9f03-9eeb987c407a.png)
