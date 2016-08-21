<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Koton Mvn Sprng Mngo</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/bootstrap4.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/modal.css" rel="stylesheet" />

</head>



<body>

    <div class="navbar navbar-static-top navbar-dark bg-inverse">
      <div class="container-fluid">
        <a href="#" data-remodal-target="modal" onclick="showCart()" class="navbar-brand">
            <i class="fa fa-shopping-cart"></i> cart
        </a>
      </div>
    </div>

    <section class="jumbotron text-xs-center jumbotron-koton">
      <div class="container">
        <h1 class="jumbotron-heading">Select Category</h1>
        <p>
          <c:forEach items="${categories}" var="category">

              <a class="btn ${selectedCategoryId==category.id?'btn-primary':'btn-secondary'}" href="/category/${category.id}" role="button">${category.name}</a>

          </c:forEach>
        </p>
      </div>
    </section>

    <div class="album text-muted">
      <div class="container">

        <div class="row">
            <c:forEach items="${products}" var="product">
                <div class="card">
                    <div class="card-product-img">
                        <p>${product.name}</p>
                    </div>
                    <p class="card-text">Price: ${product.price} TL</p>
                    <div class="card-button-container">
                        <div class="btn-group btn-group-sm">
                            <a href="/product/detail/${product.id}" class="btn btn-sm btn-secondary">Detail</a>
                            <button type="button" onclick="addOneProductToCart(${product.id})" class="btn btn-sm btn-success">
                                Add to Cart
                            </button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

      </div>
    </div>

    <div class="remodal" data-remodal-id="modal" aria-labelledby="modalTitle" aria-describedby="modalDesc">
        <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
        <div class="container">
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                    <tr>
                        <th style="width:50%">Product</th>
                        <th style="width:10%">Price</th>
                        <th style="width:8%">Quantity</th>
                        <th style="width:22%" class="text-center">Subtotal</th>
                        <th style="width:10%"></th>
                    </tr>
                </thead>
                <tbody data-cart-body>

                </tbody>
                <tfoot>
                    <tr class="visible-xs">
                        <td class="text-center"><strong>Total 1.99</strong></td>
                    </tr>
                    <tr>
                        <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
                        <td colspan="2" class="hidden-xs"></td>
                        <td class="hidden-xs text-center"><strong>Total $1.99</strong></td>
                        <td><a href="#" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js" integrity="sha384-THPy051/pYDQGanwU6poAc/hOdQxjnOEXzbT+OuUAFqNqFjL+4IGLBgCJC3ZOShY" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>

    <script src="${pageContext.request.contextPath}/assets/js/bootstrap4.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modal.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/cart.js"></script>
 
</body>
</html>