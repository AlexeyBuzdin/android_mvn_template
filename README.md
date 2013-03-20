Template project for Android with dependencies to
* RoboGuice
* Robolectric
* ActionBarSherlock

Usage:
1) Download required Android SDK through SDK Manager
2) mvn:install
3) android:deploy

To remove Robolectric Warnings at start (Optional):
1) Download https://github.com/mosabua/maven-android-sdk-deployer/#readme
2) Uncomment Android and Android maps dependency in Robolectric section
3) Comment com.google.android dependency
4) Follow maven-android-sdk-deployer tutorial to install required sdk to local repo