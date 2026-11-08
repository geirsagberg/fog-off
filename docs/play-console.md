# Play Console answers

What to enter in the Play Console for Fog Off. Keep this in step with the app: update it when a feature changes what the app collects or why it needs a permission.

## Store listing

**App name:** Fog Off

**Short description** (80 characters max):

> Clear the fog from every street you visit. Your map stays on your phone.

**Full description:**

> Fog Off covers the map in fog and clears it wherever you go. Walk, run, cycle or drive, and watch the places you have been appear street by street.
>
> • Track a trip to record a route you care about.
> • Turn on automatic tracking to clear fog as you go about your day. Fog Off collects your location in the background, even when the app is closed, to do this. It checks for movement first to save battery.
> • Draw routes you travelled before you had the app.
>
> Your location history stays on your phone. Fog Off has no account, no ads and no analytics. Back up your map to a file whenever you like.

**Privacy policy:** https://github.com/geirsagberg/fog-off/blob/main/PRIVACY.md

**Category:** Travel & Local

**Graphics:** app icon (512 × 512), feature graphic (1024 × 500), and at least two phone screenshots.

## App content

### Privacy policy

Use the URL above. The app links to it from its menu.

### Ads

No, the app has no ads.

### App access

All features are available without special access. No login is needed.

### Content rating

Pick **Utility, productivity, communication or other**. Answer **no** to every question about violence, sexuality, language, drugs, gambling and user-generated content. For the question about sharing the user's location with other users, answer **no**. The app never shares it.

### Target audience and content

Pick **18 and over** only. The app is not designed for children, and choosing younger groups adds Families policy review.

### Data safety

Fog Off's own data never leaves the device, and Play does not count data that is only processed on the device. The Google Maps SDK sends some data to Google, so declare that.

- **Does your app collect or share any of the required user data types?** Yes.
- **Is all of the user data collected by your app encrypted in transit?** Yes. The Maps SDK uses HTTPS.
- **Do you provide a way for users to request that their data is deleted?** No. There is nothing to delete on a server; on the device, users uninstall or clear storage.

Data types, all **collected**, **not shared**, **processed ephemerally: no**, **required**:

| Category | Data type | Purposes |
|---|---|---|
| App activity | App interactions | Analytics |
| App info and performance | Crash logs | Analytics |
| App info and performance | Diagnostics | Analytics |
| Device or other IDs | Device or other IDs | Analytics |

Location is not declared. Fog Off stores it only on the device, and does not send it to Google Maps or anyone else. Backups are user-initiated transfers to a place the user picks, which Play does not count as sharing.

Sources: [Maps SDK for Android data disclosure](https://developers.google.com/maps/documentation/android-sdk/play-data-disclosure), [Data safety guidance](https://support.google.com/googleplay/android-developer/answer/10787469).

### Government apps, financial features, health, news

No to each.

## Sensitive app permissions

### Foreground service: location

**Feature:** Track trip. While the user tracks a trip, the app records their route with a visible notification, including while the screen is off.

**Video:** start **Track trip**, show the notification, lock the screen, then unlock and show the recorded route.

### Location permissions

Found under **App content → Sensitive app permissions → Location permissions**. Declare one feature.

**Purpose of background location** (the feature):

> Automatic tracking clears fog from the map wherever the user goes, without having to start a trip. It is the app's main purpose: building a personal map of every place the user has visited.

**Why it needs background location:**

> Users visit most places with the app closed and the phone in their pocket. Automatic tracking waits for Android to report that the user has started moving, then takes a few location fixes and clears fog for the streets they pass. These events arrive while the app is not in use, so foreground access would leave almost every visited place fogged. The user turns automatic tracking on from the app menu, sees an in-app disclosure before any permission prompt, and can turn it off at any time. Location history stays on the device and is never sent off it.

**Video:** a YouTube link (unlisted is fine), 30 seconds or less, showing:

1. The map with fog, then **More options → Enable automatic tracking**.
2. The in-app disclosure, then **Continue**.
3. The location prompt, then **Allow all the time** in settings.
4. The app closed while the phone moves along a route.
5. The app reopened, with fog cleared along that route.
