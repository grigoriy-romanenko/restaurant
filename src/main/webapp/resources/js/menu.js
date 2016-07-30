$(document).ready(function () {
    setCategories();
});

function setCategories() {
    $.ajax({
        url: "/restaurant/menu/categories",
        success: function(response) {
            var categories = $.map(response, function (category) {
                return new Option(category.title, category.id);
            });
            $("#categories").append(categories);
            setMenuItems();
            $("#categories").change();
        },
        error: function() {
            console.error("Error while getting menu categories");
        }
    });
}

function setMenuItems() {
    $("#categories").change(function () {
        $("#items").empty();
        var categoryId = $("#categories").val();
        $.ajax({
            url: "/restaurant/menu/categories/" + categoryId + "/items",
            success: function(response) {
                var itemsList = $.map(response, function (item) {
                    return "<tr><td>" + item.title + "</td><td>" + item.price + "</td></tr>";
                });
                $("#items").append(itemsList);
            },
            error: function() {
                console.error("Error while getting menu items");
            }
        });
    });
}

