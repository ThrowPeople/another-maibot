package cat.kiwi.anothermaibot.utils

import cat.kiwi.anothermaibot.model.FlattenSongItem


fun List<FlattenSongItem>.getJi(complete: Boolean = true): List<FlattenSongItem> =
    if (complete) this.filter { it.fc.isNotEmpty() }.filter { it.levelIndex != 4 }
    else this.filter { it.fc.isEmpty() }.filter { it.levelIndex != 4 }

fun List<FlattenSongItem>.getJiang(complete: Boolean = true): List<FlattenSongItem> =
    if (complete) this.filter { it.score >= 100.0 }.filter { it.levelIndex != 4 }
    else this.filter { it.score < 100.0 }.filter { it.levelIndex != 4 }

fun List<FlattenSongItem>.getShen(complete: Boolean = true): List<FlattenSongItem> =
    if (complete) this.filter { it.fc == "ap" || it.fc == "app" }.filter { it.levelIndex != 4 }
    else this.filter { !(it.fc == "ap" || it.fc == "app") }.filter { it.levelIndex != 4 }

fun List<FlattenSongItem>.getWuWu(complete: Boolean = true): List<FlattenSongItem> =
    if (complete) this.filter { it.fs.isNotEmpty() }.filter { it.levelIndex != 4 }
    else this.filter { it.fs.isEmpty() }.filter { it.levelIndex != 4 }

fun List<FlattenSongItem>.getBaZhe(complete: Boolean = true): List<FlattenSongItem> =
    if (complete) this.filter { it.score >= 80.0 }.filter { it.levelIndex != 4 }
    else this.filter { it.score < 80.0 }.filter { it.levelIndex != 4 }
