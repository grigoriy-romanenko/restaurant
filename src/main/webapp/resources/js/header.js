$(document).ready(function () {
    $.ajax({
        url: "/restaurant/menu/categories",
        success: function (response) {
            $.each(response, function (i, category) {
                var li = document.createElement("li");
                var a = document.createElement("a");
                a.href = "/restaurant/menu/categories/" + category.id;
                a.textContent = category.title;
                li.appendChild(a);
                $(".dropdown-menu").append(li);
            });
        },
        error: function () {
            console.error("Error while getting menu categories");
        }
    });
});