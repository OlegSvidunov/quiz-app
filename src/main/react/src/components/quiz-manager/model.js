export class Model {
    constructor() {
        this.quizModel =
            [
                {
                    "quizTitle": "Киевская русь",
                    "questions": [
                        {
                            "questionTitle": "Кто такой Ярослав?",
                            "questionAnswers": [
                                {
                                    "answerTitle": "Тринадцатый пророк созвездия сейлормун",
                                    "isCorrect": true
                                },
                                {
                                    "answerTitle": "Человек",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Тот самый с пяти гривневой купюры",
                                    "isCorrect": false
                                }
                            ]
                        },
                        {
                            "questionTitle": "Год крещения руси?",
                            "questionAnswers": [
                                {
                                    "answerTitle": "1318",
                                    "isCorrect": true
                                },
                                {
                                    "answerTitle": "Не момню",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Я баптист",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Ни одни ответ не является правильным",
                                    "isCorrect": true
                                }
                            ]
                        },
                        {
                            "questionTitle": "Что такое пончик?",
                            "questionAnswers": [
                                {
                                    "answerTitle": "пирожок",
                                    "isCorrect": true
                                },
                                {
                                    "answerTitle": "Средство от забывчивости",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Пончик - это как пончо, только с другим окончанием",
                                    "isCorrect": false
                                }
                            ]
                        }
                    ]
                },
                {
                    "quizTitle": "Тачки",
                    "questions": [
                        {
                            "questionTitle": "Кто быстрее я или ты?",
                            "questionAnswers": [
                                {
                                    "answerTitle": "Я",
                                    "isCorrect": true
                                },
                                {
                                    "answerTitle": "Ты",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Тот самый с пяти гривневой купюры",
                                    "isCorrect": false
                                }
                            ]
                        },
                        {
                            "questionTitle": "Почему небо голубое?",
                            "questionAnswers": [
                                {
                                    "answerTitle": "Потому что",
                                    "isCorrect": true
                                },
                                {
                                    "answerTitle": "Не задавай глупых вопросов",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Я баптист",
                                    "isCorrect": false
                                },
                                {
                                    "answerTitle": "Ни одни ответ не является правильным",
                                    "isCorrect": true
                                }
                            ]
                        }
                    ]
                }
            ]
    }

    getModel() {
        return this.quizModel;
    }
}