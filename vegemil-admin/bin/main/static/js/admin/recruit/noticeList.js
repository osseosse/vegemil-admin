/**
 * DataTables Basic
 */
 
 'use strict';

// Advanced Search Functions Starts
// --------------------------------------------------------------------

// Filter column wise function
 
function filterColumn(val) {

    var startDate = $('.start_date').val(),
      endDate = $('.end_date').val();
      
      if (startDate !== '' && endDate !== '') {
        filterByDate(5, startDate, endDate); // We call our filter function
      }

    $('.datatables-basic').dataTable().fnDraw();

}

function copyNotice(sTh){
	if(confirm('복사하시겠습니까?')){
		$.ajax({
			url : '/admin/recruit/noticeCopy?sTh=' + sTh,
			type : "get",
			dataType : "json",
			success : function(data) {
				if(data == "false"){
					alert("채용공고 복사에 실패했습니다.");
				}
				else{
					alert("채용공고 복사에 성공했습니다.");
					window.location.reload();
					$('.datatables-basic').DataTable().ajax.reload();
				}
				
			},
			error : function(){
			}
		});
	}
}

// Datepicker for advanced filter
var separator = ' - ',
  rangePickr = $('.flatpickr-range'),
  dateFormat = 'yyyy-MM-dd';
var options = {
  autoUpdateInput: false,
  autoApply: true,
  locale: {
    format: dateFormat,
    separator: separator
  },
  opens: $('html').attr('data-textdirection') === 'rtl' ? 'left' : 'right'
};

//
if (rangePickr.length) {
  rangePickr.flatpickr({
    mode: 'range',
    dateFormat: 'Y-m-d',
    onClose: function (selectedDates, dateStr, instance) {
      var startDate = '',
        endDate = new Date();
        
      var month_s,date_s,month_e,date_e;
      
      if((selectedDates[0].getMonth() + 1) < 10) month_s = '0'+(selectedDates[0].getMonth() + 1);
      else month_s = (selectedDates[0].getMonth() + 1);
      if(selectedDates[0].getDate() < 10) date_s = '0'+selectedDates[0].getDate();
      else date_s = selectedDates[0].getDate();
        
      if (selectedDates[0] != undefined) {
        startDate =
          selectedDates[0].getFullYear() + '-' + month_s + '-' + date_s;
        $('.start_date').val(startDate);
      }

      if((selectedDates[1].getMonth() + 1) < 10) month_e = '0'+(selectedDates[1].getMonth() + 1);
      else month_e = (selectedDates[1].getMonth() + 1);
      if(selectedDates[1].getDate() < 10) date_e = '0'+selectedDates[1].getDate();
      else date_e = selectedDates[1].getDate();
      
      if (selectedDates[1] != undefined) {
        endDate =
          selectedDates[1].getFullYear() + '-' + month_e + '-' + date_e;
        $('.end_date').val(endDate);
      }
      $(rangePickr).trigger('change').trigger('keyup');
    }
  });
}

// Advance filter function
// We pass the column location, the start date, and the end date
var filterByDate = function (column, startDate, endDate) {
  // Custom filter syntax requires pushing the new filter to the global filter array
  $.fn.dataTableExt.afnFiltering.push(function (oSettings, aData, iDataIndex) {
    var rowDate = normalizeDate(aData[column]),
      start = normalizeDate(startDate),
      end = normalizeDate(endDate);
	
    // If our date from the row is between the start and end
    if (start <= rowDate && rowDate <= end) {
      return true;
    } else if (rowDate >= start && end === '' && start !== '') {
      return true;
    } else if (rowDate <= end && start === '' && end !== '') {
      return true;
    } else {
      return false;
    }
  });
};

// converts date strings to a Date object, then normalized into a YYYYMMMDD format (ex: 20131220). Makes comparing dates easier. ex: 20131220 > 20121220
var normalizeDate = function (dateString) {
  //var date = new Date(dateString);
  var normalized = dateString.slice(0,4) + dateString.slice(5,7) + dateString.slice(8,10);
    //date.getFullYear() + '' + ('0' + (date.getMonth() + 1)).slice(-2) + '' + ('0' + date.getDate()).slice(-2);
  return normalized;
};
// Advanced Search Functions Ends

$(function () {

  var dt_basic_table = $('.noticeList'),
    dt_date_table = $('.dt-date');

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      ajax: '/admin/recruit/noticeList/table',
      columns: [
      	{ data: 'sTh' },
        { data: 'sTh' },
        { data: 'sTh' },
        { data: 'sTitle' },
        { data: 'sClose' },
        { data: 'sInsertdate' }
      ],
      columnDefs: [
      	{
      		targets: 0,
      		className: "align-center",
      		orderable: false,
          	render: function (data, type, full, meta) {
	            return (
	              '<div class="form-check">' +
		              '<input type="checkbox" class="form-check-input" id="customCheck2" name="checkList"/>' +
		              '<label class="form-check-label" for="customCheck2"></label>' +
	              '</div>'
	            );
	        }
      	},
      	{
      		targets: 1,
      		className: "text-center",
      		orderable: false,
          	render: function (data, type, full, meta) {
	            return '<button type="button" class="btn btn-outline-primary btn-sm" onclick="location.href=\'/admin/recruit/noticeAdd?sTh=' + full['sTh'] + '\'">수정</button>';
	        }
      	},
      	{
      		targets: 2,
      		orderable: false,
          	render: function (data, type, full, meta) {
	            return '<button type="button" class="btn btn-outline-success btn-sm" onclick="copyNotice(\''+ full['sTh'] + '\');">복사</button>';
	        }
      	},
      	{
      		targets: 3,
      		className: "text-left",
      		orderable: false,
          	render: function (data, type, full, meta) {
	            return (
	              '<a href="01_recruit03.html">' +
		              full['sTitle'] +
	              '</a>'
	            );
	        }
      	},
      	{
      		targets: 4,
      		orderable: false,
          	render: function (data, type, full, meta) {
				if(full['sClose'] == '1')	return('<span class="badge rounded-pill badge-light-info">진행</span>');
				else 						return('<span class="badge rounded-pill badge-light-warning">마감</span>');
	        }
      	},
      	{
      		targets: 5,
      		orderable: false,
          	render: function (data, type, full, meta) {
				return(full['sInsertdate'].substr(0,10));
	        }
      	}
      ],
      order: [[0, 'desc']],
      dom:
        '<"card-header border-bottom p-1"><"d-flex justify-content-between align-items-center mx-0 row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6"f>>t<"d-flex justify-content-between mx-0 row"<"col-sm-12 col-md-6"p>>',
      displayLength: 7,
      lengthMenu: [7, 10, 25, 50, 75, 100],
      language: {
      	search : '검색',
        paginate: {
          previous: '',
          next: ''
        }
      },
      info: false
    });
    $('input.dt-input').on('keyup', function () {
	    filterColumn($(this).val());
	  });
  }

});
