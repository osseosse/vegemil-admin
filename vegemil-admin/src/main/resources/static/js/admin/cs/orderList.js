/**
 * DataTables Basic
 */
 
 'use strict';

$(function () {
//가정배달 주문 리스트
  var dt_basic_table = $('#orderListTable');

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      ajax: '/admin/cs/orderList/table',
      columns: [
      	{ data: 'oIdx' },
        { data: 'oOrderdate' },
        { data: 'oName' },
        { data: 'oHp' },
        { data: 'oAddr' },
        { data: 'oProduct' },
        { data: 'oSize' },
        { data: 'oEa' },
        { data: 'oDay' },
        { data: 'oAction' },
        { data: '' }
      ],
      columnDefs: [
		{
			targets: 2,
			orderable: false
		},
		{
			targets: 3,
			orderable: false
      	},
        {
			targets: 4,
			orderable: false
		},
		{
			targets: 5,
			orderable: false
		},
		{
			targets: 6,
			orderable: false
		},
		{
			targets: 7,
			orderable: false
		},
		{
			targets: 8,
			orderable: false
		},
		{
			targets: 9,
			render: function (data, type, full, meta) {
				var check;
				
				if(full['oAction']=='1')	check='접수완료';
      			else	check='접수중';
      			return(
      				check + 
					'<div class="dropdown">' +
					'<button type="button" class="btn btn-sm text-white dropdown-toggle hide-arrow" data-bs-toggle="dropdown">' +
					'<i data-feather="more-vertical"></i>' +
					'</button>' +
					'<div class="dropdown-menu dropdown-menu-right">' +
					'<form action="/admin/event/calendarmodel/update?cIdx=' + full['cIdx'] + '&cRank=1" method="post" enctype="multipart/form-data">' + 
					'<input type="submit" class="btn btn-outline-primary" value="접수중"></input>' +
					'</form>' + 
					'<form action="/admin/event/calendarmodel/update?cIdx=' + full['cIdx'] + '&cRank=2" method="post" enctype="multipart/form-data">' + 
					'<input type="submit" class="btn btn-outline-primary" value="접수완료"></input>' +
					'</form>' + 
					'</div>' 
      			);
      		}
		},
  		{
      		targets: 10,
      		orderable: false,
			render: function (data, type, full, meta) {
				return(
					'<a href="/admin/cs/cpNewsDelete?pIdx=' + full['pIdx'] + '">' + 
					'<button type="submit" class="btn btn-outline-primary">' +
					'<i data-feather="trash-2"></i>' + 
					'<span>삭제</span>' + 
					'</button>' + 
					'</a>'
      			);
      		}
  		}
      ],
      order: [[0, 'desc']],
      dom:
        '<"card-header border-bottom p-1"<"head-label"><"d-flex justify-content-between align-items-center mx-0 row"<"col-sm-12 col-md-6"l>>>t<"d-flex justify-content-between mx-0 row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
      displayLength: 7,
      lengthMenu: [7, 10, 25, 50, 75, 100],
      language: {
        paginate: {
          // remove previous & next text from pagination
          previous: '&nbsp;',
          next: '&nbsp;'
        }
      }
    });
    $('div.head-label').html('<h6 class="mb-0">가정배달 주문 리스트</h6>');
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }
  
  //제품등록 리스트
  var dt_basic_table = $('#productTable');

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      ajax: '/admin/cs/orderList/product',
      columns: [
      	{ data: 'pTitle' },
        { data: 'pSize' },
        { data: 'pImage' },
        { data: 'pUri' },
        { data: '' }
      ],
      columnDefs: [
		{
			targets: 2,
			orderable: false
		},
		{
			targets: 3,
			orderable: false
      	},
  		{
      		targets: 4,
      		orderable: false,
			render: function (data, type, full, meta) {
				return(
					'<a href="/admin/cs/cpNewsDelete?pIdx=' + full['pIdx'] + '">' + 
					'<button type="submit" class="btn btn-outline-primary">' +
					'<i data-feather="trash-2"></i>' + 
					'<span>삭제</span>' + 
					'</button>' + 
					'</a>'
      			);
      		}
  		}
      ],
      order: [[0, 'desc']],
      dom:
        '<"card-header border-bottom p-1"<"head-label"><"d-flex justify-content-between align-items-center mx-0 row"<"col-sm-12 col-md-6"l>>>t<"d-flex justify-content-between mx-0 row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
      displayLength: 7,
      lengthMenu: [7, 10, 25, 50, 75, 100],
      language: {
        paginate: {
          // remove previous & next text from pagination
          previous: '&nbsp;',
          next: '&nbsp;'
        }
      }
    });
    $('div.head-label').html('<h6 class="mb-0">가정배달 주문 리스트</h6>');
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }
  
  var quantityCounter = $('.quantity-counter'),
    CounterMin = 1,
    CounterMax = 10,
    bsStepper = document.querySelectorAll('.bs-stepper'),
    checkoutWizard = document.querySelector('.checkout-tab-steps'),
    removeItem = $('.remove-wishlist'),
    moveToCart = $('.move-cart'),
    isRtl = $('html').attr('data-textdirection') === 'rtl';

  // remove items from wishlist page
  removeItem.on('click', function () {
    $(this).closest('.ecommerce-card').remove();
    toastr['error']('', 'Removed Item 🗑️', {
      closeButton: true,
      tapToDismiss: false,
      rtl: isRtl
    });
  });

  // move items to cart
  moveToCart.on('click', function () {
    $(this).closest('.ecommerce-card').remove();
    toastr['success']('', 'Added to wishlist ❤️', {
      closeButton: true,
      tapToDismiss: false,
      rtl: isRtl
    });
  });

  // Checkout Wizard

  // Adds crossed class
  if (typeof bsStepper !== undefined && bsStepper !== null) {
    for (var el = 0; el < bsStepper.length; ++el) {
      bsStepper[el].addEventListener('show.bs-stepper', function (event) {
        var index = event.detail.indexStep;
        var numberOfSteps = $(event.target).find('.step').length - 1;
        var line = $(event.target).find('.step');

        // The first for loop is for increasing the steps,
        // the second is for turning them off when going back
        // and the third with the if statement because the last line
        // can't seem to turn off when I press the first item. ¯\_(ツ)_/¯

        for (var i = 0; i < index; i++) {
          line[i].classList.add('crossed');

          for (var j = index; j < numberOfSteps; j++) {
            line[j].classList.remove('crossed');
          }
        }
        if (event.detail.to == 0) {
          for (var k = index; k < numberOfSteps; k++) {
            line[k].classList.remove('crossed');
          }
          line[0].classList.remove('crossed');
        }
      });
    }
  }

  // Init Wizard
  if (typeof checkoutWizard !== undefined && checkoutWizard !== null) {
    var wizard = new Stepper(checkoutWizard, {
      linear: false
    });

    $(checkoutWizard)
      .find('.btn-next')
      .each(function () {
        $(this).on('click', function (e) {
          wizard.next();
        });
      });

    $(checkoutWizard)
      .find('.btn-prev')
      .on('click', function () {
        wizard.previous();
      });
  }

  // checkout quantity counter
  if (quantityCounter.length > 0) {
    quantityCounter
      .TouchSpin({
        min: CounterMin,
        max: CounterMax
      })
      .on('touchspin.on.startdownspin', function () {
        var $this = $(this);
        $('.bootstrap-touchspin-up').removeClass('disabled-max-min');
        if ($this.val() == 1) {
          $(this).siblings().find('.bootstrap-touchspin-down').addClass('disabled-max-min');
        }
      })
      .on('touchspin.on.startupspin', function () {
        var $this = $(this);
        $('.bootstrap-touchspin-down').removeClass('disabled-max-min');
        if ($this.val() == 10) {
          $(this).siblings().find('.bootstrap-touchspin-up').addClass('disabled-max-min');
        }
      });
  }

});
