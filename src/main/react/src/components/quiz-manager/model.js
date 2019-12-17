export class Model {
    constructor() {
        this.quizModel =
            [
                {
                    "quizId": 1,
                    "quizTitle": "Киевская русь",
                    "questions": [
                        {
                            "questionId": 1,
                            "questionTitle": "Кто такой Ярослав?",
                            "questionAnswers": [
                                {
                                    "answerId": 1,
                                    "answerTitle": "Тринадцатый пророк созвездия сейлормун",
                                    "isCorrect": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Человек",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Тот самый с пяти гривневой купюры",
                                    "isCorrect": false
                                }
                            ]
                        },
                        {
                            "questionId": 2,
                            "questionTitle": "Год крещения руси?",
                            "questionAnswers": [
                                {
                                    "answerId": 1,
                                    "answerTitle": "1318",
                                    "isCorrect": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Не момню",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Я баптист",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 4,
                                    "answerTitle": "Ни одни ответ не является правильным",
                                    "isCorrect": true
                                }
                            ]
                        },
                        {
                            "questionId": 3,
                            "questionTitle": "Что такое пончик?",
                            "questionAnswers": [
                                {
                                    "answerId": 1,
                                    "answerTitle": "пирожок",
                                    "isCorrect": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Средство от забывчивости",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Пончик - это как пончо, только с другим окончанием",
                                    "isCorrect": false
                                }
                            ]
                        }
                    ]
                },
                {
                    "quizId": 2,
                    "quizTitle": "Тачки",
                    "questions": [
                        {
                            "questionId": 1,
                            "questionTitle": "Кто быстрее я или ты?",
                            "questionAnswers": [
                                {
                                    "answerId": 1,
                                    "answerTitle": "Я",
                                    "isCorrect": true
                                },
                                {
                                    "answerId": 5,
                                    "answerTitle": "Ты",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 6,
                                    "answerTitle": "Тот самый с пяти гривневой купюры",
                                    "isCorrect": false
                                }
                            ]
                        },
                        {
                            "questionId": 2,
                            "questionTitle": "Почему небо голубое?",
                            "questionAnswers": [
                                {
                                    "answerId": 1,
                                    "answerTitle": "Потому что",
                                    "isCorrect": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Не задавай глупых вопросов",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Я баптист",
                                    "isCorrect": false
                                },
                                {
                                    "answerId": 4,
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