var count ;
var sum;
var addProductBtn = "<a href='addProduct.jsp'><input type=submit class = form_submit id = to-add-product-btn value='Добавить товар'></a>";

function prepareWishList() {
    count=0;

    var request = {
        user_id: id
    };
    $.ajax({
        url: "GetProductList",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(request)},
        success: function (data) {
            sum = 0;
            count = data.length;
            for (var i = 0; i < data.length; i++) {
                sum += data[i].price;
            }
            switch (type)
            {
                case 0: {
                    document.getElementById('prod-type').innerHTML = "Список Желаний";
                    document.getElementById('prod-count').innerHTML = "Кол-во товаров: "+ count;
                    document.getElementById('prod-sum').innerHTML = "Сумма : "+sum + " руб";
                    $('#block-wish_list').show();
                }
                    break;
                case 1:{
                    document.getElementById('prod-type').innerHTML = "Список Товаров";
                    document.getElementById('prod-count').innerHTML = "Кол-во товаров: "+count ;
                    document.getElementById('add-prod-btn').innerHTML = addProductBtn;

                    $('#block-wish_list').show();

                }
                    break;
            }
        }
    });

}