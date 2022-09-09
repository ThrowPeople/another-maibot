package cat.kiwi.anothermaibot.repository

import cat.kiwi.anothermaibot.model.Achievement

interface PlayerAchievement {
    fun getAchievementByVersion(id: Long, version: String): Achievement
}