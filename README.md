# spotifyi3

Spotify CLI for Linux required: `(sudo) pip install spotify-cli-linux`

## Building

`git clone https://github.com/TsundereBug/spotifyi3 && cd spotifyi3`

`sbt assemble`

There will now be a `spotifyi3-assembly-0.1.jar` under `target/scala-2.12/`.

## Enabling

Create a new directory anywhere you want. I will be calling this directory
`$cdir`.

Find your i3 config (most likely under `~/.config/i3/config`). Set the part
with `bar` like this:
```
# Start i3bar to display a workspace bar (plus the system information i3status
# finds out, if available)
bar {
        status_command $cdir/spotbar.sh
}
```
Now copy the `spotifyi3-assembly-0.1.jar` to `$cdir` with the name
`spotifyi3.jar`.
[Create a config file
named `$conf.conf` in `$cidr`.](https://i3wm.org/i3status/manpage.html)
Make sure this configuration sets i3status to `i3bar` mode or
else JSON parsing will fail and you will get errors!
Then, create `$cdir/spotbar.sh` with the following content:
```sh
#!/bin/sh
# shell script to prepend i3status with spotify song and artist
i3status --config $cdir/$conf.conf | java -jar $cdir/spotifyi3.jar
```
Remember to replace `$cdir` and `$conf.conf` with the actual locations
in these files.

Restart i3 to restart the bar using `meta+shift+R`.
