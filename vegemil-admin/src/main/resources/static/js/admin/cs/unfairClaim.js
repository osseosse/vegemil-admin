/**
 * DataTables Basic
 */
 
 'use strict';

$(function () {

  var dt_basic_table = $('.datatables-basic'),
    dt_date_table = $('.dt-date');

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      ajax: '/admin/cs/unfairClaim/table',
      columns: [
      	{ data: 'cWritedate' },
      	{ data: 'cCheck' },
        { data: 'cName' },
        { data: 'cSubject' },
        { data: 'cHp' },
        { data: 'cEmail' },
        { data: '' }
      ],
      columnDefs: [
      	{
      		targets: 0,
			render: function (data, type, full, meta) {
				return full['cWritedate']+full['cWritetime'];
      		},
			targets: 1,
			render: function (data, type, full, meta) {
				var check;
				
				if(full['cCheck']=='1')	check='완료';
      			else	check='접수중';
      			return check;
      		}
		},
		{
			targets: 2,
			orderable: false
		},
		{
			targets: 3,
			orderable: false,
			render: function (data, type, full, meta) {
				return(
					'<a href="/admin/cs/unfairClaimDetail?cIdx=' + full['cIdx'] + '">' +
					full['cSubject'] + 
					'</a>'
      			);
      		}
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
			orderable: false,
			render: function (data, type, full, meta) {
				return(
					'<a href="/admin/cs/unfairClaimDelete?cIdx=' + full['cIdx'] + '">' + 
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
        '<"card-header border-bottom p-1"<"head-label">><"d-flex justify-content-between align-items-center mx-0 row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6"f>>t<"d-flex justify-content-between mx-0 row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
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
    $('div.head-label').html('<h6 class="mb-0">불공정거래 신고</h6>');
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }

});
