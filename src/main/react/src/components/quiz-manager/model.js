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
                                    "correct": true
                                },
                                {
                                    "answerId": 5,
                                    "answerTitle": "Человек",
                                    "correct": false
                                },
                                {
                                    "answerId": 6,
                                    "answerTitle": "Тот самый с пяти гривневой купюры",
                                    "correct": false
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
                                    "correct": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Не момню",
                                    "correct": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Я баптист",
                                    "correct": false
                                },
                                {
                                    "answerId": 4,
                                    "answerTitle": "Ни одни ответ не является правильным",
                                    "correct": true
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
                                    "correct": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Средство от забывчивости",
                                    "correct": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Пончик - это как пончо, только с другим окончанием",
                                    "correct": false
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
                                    "correct": true
                                },
                                {
                                    "answerId": 5,
                                    "answerTitle": "Ты",
                                    "correct": false
                                },
                                {
                                    "answerId": 6,
                                    "answerTitle": "Тот самый с пяти гривневой купюры",
                                    "correct": false
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
                                    "correct": true
                                },
                                {
                                    "answerId": 2,
                                    "answerTitle": "Не задавай глупых вопросов",
                                    "correct": false
                                },
                                {
                                    "answerId": 3,
                                    "answerTitle": "Я баптист",
                                    "correct": false
                                },
                                {
                                    "answerId": 4,
                                    "answerTitle": "Ни одни ответ не является правильным",
                                    "correct": true
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