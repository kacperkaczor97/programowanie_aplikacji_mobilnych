package com.example.bmicalculatorkotlin

object Constants{
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<Question>{
        val questionsList=ArrayList<Question>()

        // 1
        val que1=Question(1,
                "Od czego zależy ilość spożywanych kalorii w posiłku w ciągu dnia?",
                "Od płci i aktywności",
                "Od kraju",
                "Od nas",
                "nie mamy na to wplywu",
                1
        )
        questionsList.add(que1)
        // 2
        val que2 = Question(
                2, "Co znajduję się na samym dole nowej piramidy zdrowego żywienia?",
                "owoce", "warzywa",
                "Aktywność fizyczna", "białko", 3
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
                3, "Co jest głównym źródłem witaminy C dla statystycznego Polaka?",
                "pomarańcze", "banany",
                "jabłka", "Ziemniaki", 4
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
                4, "Jak duża powinna być dzienna porcja warzyw i owoców?",
                "100g", "500g",
                "250g", "1000g", 2
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
                5, "Dzienna dawka soli nie powinna przekraczać?",
                "1g", "8g",
                "5g", "10g", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
                6, "Ile posiłków dziennie powinien zjadać człowiek ?",
                "5", "2",
                "1", "3", 1
        )

        questionsList.add(que6)

        return questionsList

    }
}