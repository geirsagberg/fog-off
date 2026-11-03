# Releasing to Google Play

Fog Off uses Play App Signing. Google holds the app signing key. Releases are signed with a private upload key that stays outside the repository.

Debug builds use the application ID `net.sagberg.fogoff.debug`, so they install next to the Play build.

## One-time setup

1. Create an upload key outside the repository:

   ```sh
   keytool -genkeypair -v -keystore ~/keys/fogoff-upload.jks \
     -alias upload -keyalg RSA -keysize 4096 -validity 10000
   ```

   Back up the keystore and its passwords. If the upload key is lost, Play support can reset it.

2. Add the `UPLOAD_*` values from `local.properties.example` to `local.properties`.
3. In the Play Console, create the app with package name `net.sagberg.fogoff` and keep Play App Signing turned on.
4. After the first upload, copy the app signing SHA-1 from **Test and release → App integrity**. Add it to the Google Maps API key restrictions for `net.sagberg.fogoff`. Debug builds also need `net.sagberg.fogoff.debug` with the debug SHA-1.

## Release a build

1. Increase `versionCode` in `app/build.gradle.kts`. Play rejects a version code it has already seen. Update `versionName` when the change is worth a new name.
2. Build the bundle:

   ```sh
   ./gradlew :app:bundleRelease
   ```

3. Upload `app/build/outputs/bundle/release/app-release.aab` to the closed testing track in the Play Console.

## Play Console requirements

Closed testing releases are reviewed. Before the first review, complete:

- the background location permission declaration, including a short video that shows the feature using background location
- a privacy policy URL
- the Data safety form
- the content rating questionnaire
- the target audience
