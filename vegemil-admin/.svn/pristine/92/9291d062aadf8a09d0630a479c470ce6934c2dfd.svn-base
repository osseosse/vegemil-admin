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
      ajax: '/admin/cs/cpNews/table',
      columns: [
      	{ data: 'pWritedate' },
        { data: 'pSubject' },
        { data: 'pFname' },
        { data: 'pHit' },
        { data: '' }
      ],
      columnDefs: [
      	{
			targets: 1,
			orderable: false
		},
		{
			targets: 2,
			orderable: false,
			render: function (data, type, full, meta) {
      			return(
      				'<a href="/admin/cs/cpNewsDownload?fileName=' + full['pFname'] + '" target="_blank">' + full['pFname'] + '</a>'
      			);
      			
      		}
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
    $('div.head-label').html('<h6 class="mb-0">CP 뉴스브리핑 - 리스트</h6>');
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }

});
