
KOTON = typeof KOTON == "undefined"? {} : KOTON;

function addOneProductToCart(id) {

	updateCart("updateProductAmountInCart/"+id+"/"+1+"/false");
}
function updateProductAmountInCart(id,amount) {

	updateCart("updateProductAmountInCart/"+id+"/"+amount+"/true");
}

function updateCart(requestPath, methodType){
    $.ajax({
        type : methodType,
        contentType : "application/json",
        url : "/cart/"+requestPath,
        timeout : 100000,
        success: function(data) {
            console.log("SUCCESS: ", data);
            KOTON.cart = data;
        },
        error: function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function cartToHtml(){

    var total=0,kdv=0,rows = "";

    if(KOTON.cart){

        for(var i=0; i<KOTON.cart.length; i++){
            var p = KOTON.cart[i].product;

            var
            id = p.id;
            name = p.name,
            price = p.price,
            size = p.size,
            amount=KOTON.cart[i].amount;

            var subTotal = amount * p.price;

            rows +='<tr>'+
                '<td data-th="Product">'+
                '    <div class="row">'+
                '        <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/60x60" alt="..." class="img-responsive"/></div>'+
                '        <div class="col-sm-10">'+
                '            <h4 class="nomargin">'+name+'</h4>'+
                '            <p>Size: '+size+'</p>'+
                '        </div>'+
                '    </div>'+
                '</td>'+
                '<td data-th="Price">'+price+'</td>'+
                '<td data-th="Quantity">'+
                '    <input type="number" id="cart-amount-'+id+'" class="form-control text-center" value="'+amount+'">'+
                '</td>'+
                '<td data-th="Subtotal" class="text-center">'+subTotal+'</td>'+
                '<td class="actions" data-th="">'+
                '    <button class="btn btn-info btn-sm" onclick="onConfirmNewAmount('+id+')"><i class="fa fa-refresh"></i></button>'+
                '    <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>'+
                '</td>'+
            '</tr>';
        }
    }
    $("tbody[data-cart-body]").html(rows);
}

function onConfirmNewAmount(id){
    var amount = $('#cart-amount-'+id).val();
    updateProductAmountInCart(id,amount);
}

function showCart(){
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/cart/get",
        timeout : 100000,
        success: function(data) {
            KOTON.cart = data;
            cartToHtml();
        }
    });

}