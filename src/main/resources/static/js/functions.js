function prepareFormDataInJson() { // вынес сериализацию формы в JSON  в отдельную ф-цию. В статье и видео я дам ссылку на весь код на ГитХабе
    var frm = $("#add_form").serializeArray(); // хватаем форму по id. Сериализуем в массив
    var obj = {}; // создаем пустой объект
    for (var a = 0; a < frm.length; a++) { // бегаем по форме (по массиву)
        obj[frm[a].name] = frm[a].value; // добавляем в объект элементы массива
    }
    return JSON.stringify(obj); // преобразуем в JSON
}
