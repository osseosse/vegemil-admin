/*=========================================================================================
    File Name: chart-chartjs.js
    Description: Chartjs Examples
    ----------------------------------------------------------------------------------------
    Item Name: Vuexy  - Vuejs, HTML & Laravel Admin Dashboard Template
    Author: PIXINVENT
    Author URL: http://www.themeforest.net/user/pixinvent
==========================================================================================*/

$(window).on('load', function () {
  'use strict';

  var chartWrapper = $('.chartjs'),
    flatPicker = $('.flat-picker'),
    barChartEx = $('.bar-chart-ex'),
    horizontalBarChartEx = $('.horizontal-bar-chart-ex'),
    lineChartEx = $('.line-chart-ex'),
    radarChartEx = $('.radar-chart-ex'),
    polarAreaChartEx = $('.polar-area-chart-ex'),
    bubbleChartEx = $('.bubble-chart-ex'),
    doughnutChartEx = $('.doughnut-chart-ex'),
    scatterChartEx = $('.scatter-chart-ex'),
    lineAreaChartEx = $('.line-area-chart-ex');

  // Color Variables
  var primaryColorShade = '#836AF9',
    yellowColor = '#ffe800',
    successColorShade = '#28dac6',
    warningColorShade = '#ffe802',
    warningLightColor = '#FDAC34',
    infoColorShade = '#299AFF',
    greyColor = '#4F5D70',
    blueColor = '#2c9aff',
    blueLightColor = '#84D0FF',
    greyLightColor = '#EDF1F4',
    tooltipShadow = 'rgba(0, 0, 0, 0.25)',
    lineChartPrimary = '#666ee8',
    lineChartDanger = '#ff4961',
    labelColor = '#6e6b7b',
    grid_line_color = 'rgba(200, 200, 200, 0.2)'; // RGBA color helps in dark layout

  // Detect Dark Layout
  if ($('html').hasClass('dark-layout')) {
    labelColor = '#b4b7bd';
  }

  // Wrap charts with div of height according to their data-height
  if (chartWrapper.length) {
    chartWrapper.each(function () {
      $(this).wrap($('<div style="height:' + this.getAttribute('data-height') + 'px"></div>'));
    });
  }

  // Init flatpicker
  if (flatPicker.length) {
    var date = new Date();
    flatPicker.each(function () {
      $(this).flatpickr({
        mode: 'range',
        defaultDate: ['2019-05-01', '2019-05-10']
      });
    });
  }

  //Draw rectangle Bar charts with rounded border
  Chart.elements.Rectangle.prototype.draw = function () {
    var ctx = this._chart.ctx;
    var viewVar = this._view;
    var left, right, top, bottom, signX, signY, borderSkipped, radius;
    var borderWidth = viewVar.borderWidth;
    var cornerRadius = 20;
    if (!viewVar.horizontal) {
      left = viewVar.x - viewVar.width / 2;
      right = viewVar.x + viewVar.width / 2;
      top = viewVar.y;
      bottom = viewVar.base;
      signX = 1;
      signY = top > bottom ? 1 : -1;
      borderSkipped = viewVar.borderSkipped || 'bottom';
    } else {
      left = viewVar.base;
      right = viewVar.x;
      top = viewVar.y - viewVar.height / 2;
      bottom = viewVar.y + viewVar.height / 2;
      signX = right > left ? 1 : -1;
      signY = 1;
      borderSkipped = viewVar.borderSkipped || 'left';
    }

    if (borderWidth) {
      var barSize = Math.min(Math.abs(left - right), Math.abs(top - bottom));
      borderWidth = borderWidth > barSize ? barSize : borderWidth;
      var halfStroke = borderWidth / 2;
      var borderLeft = left + (borderSkipped !== 'left' ? halfStroke * signX : 0);
      var borderRight = right + (borderSkipped !== 'right' ? -halfStroke * signX : 0);
      var borderTop = top + (borderSkipped !== 'top' ? halfStroke * signY : 0);
      var borderBottom = bottom + (borderSkipped !== 'bottom' ? -halfStroke * signY : 0);
      if (borderLeft !== borderRight) {
        top = borderTop;
        bottom = borderBottom;
      }
      if (borderTop !== borderBottom) {
        left = borderLeft;
        right = borderRight;
      }
    }

    ctx.beginPath();
    ctx.fillStyle = viewVar.backgroundColor;
    ctx.strokeStyle = viewVar.borderColor;
    ctx.lineWidth = borderWidth;
    var corners = [
      [left, bottom],
      [left, top],
      [right, top],
      [right, bottom]
    ];

    var borders = ['bottom', 'left', 'top', 'right'];
    var startCorner = borders.indexOf(borderSkipped, 0);
    if (startCorner === -1) {
      startCorner = 0;
    }

    function cornerAt(index) {
      return corners[(startCorner + index) % 4];
    }

    var corner = cornerAt(0);
    ctx.moveTo(corner[0], corner[1]);

    for (var i = 1; i < 4; i++) {
      corner = cornerAt(i);
      var nextCornerId = i + 1;
      if (nextCornerId == 4) {
        nextCornerId = 0;
      }

      var nextCorner = cornerAt(nextCornerId);

      var width = corners[2][0] - corners[1][0],
        height = corners[0][1] - corners[1][1],
        x = corners[1][0],
        y = corners[1][1];

      var radius = cornerRadius;

      if (radius > height / 2) {
        radius = height / 2;
      }
      if (radius > width / 2) {
        radius = width / 2;
      }

      if (!viewVar.horizontal) {
        ctx.moveTo(x + radius, y);
        ctx.lineTo(x + width - radius, y);
        ctx.quadraticCurveTo(x + width, y, x + width, y + radius);
        ctx.lineTo(x + width, y + height - radius);
        ctx.quadraticCurveTo(x + width, y + height, x + width, y + height);
        ctx.lineTo(x + radius, y + height);
        ctx.quadraticCurveTo(x, y + height, x, y + height);
        ctx.lineTo(x, y + radius);
        ctx.quadraticCurveTo(x, y, x + radius, y);
      } else {
        ctx.moveTo(x + radius, y);
        ctx.lineTo(x + width - radius, y);
        ctx.quadraticCurveTo(x + width, y, x + width, y + radius);
        ctx.lineTo(x + width, y + height - radius);
        ctx.quadraticCurveTo(x + width, y + height, x + width - radius, y + height);
        ctx.lineTo(x + radius, y + height);
        ctx.quadraticCurveTo(x, y + height, x, y + height);
        ctx.lineTo(x, y + radius);
        ctx.quadraticCurveTo(x, y, x, y);
      }
    }

    ctx.fill();
    if (borderWidth) {
      ctx.stroke();
    }
  };
  $.ajax({
		url: '/admin/cs/cpEbookCounter/chart',
		type: 'POST',
		dataType: 'json',
		success: function(data){
			var x = [];
			var y = [];
			
			for(var i=0;i<data.data.length;i++){
				x[i]=data.data[i].cCount;
			}
			
			for(var i=0;i<data.data.length;i++){
				y[i]=data.data[i].cName;
			}

			// Horizontal Bar Chart
			// --------------------------------------------------------------------
			if (horizontalBarChartEx.length) {
			  new Chart(horizontalBarChartEx, {
			    type: 'horizontalBar',
			    options: {
			      elements: {
			        rectangle: {
			          borderWidth: 2,
			          borderSkipped: 'right'
			        }
			      },
			      tooltips: {
			        // Updated default tooltip UI
			        shadowOffsetX: 1,
			        shadowOffsetY: 1,
			        shadowBlur: 8,
			        shadowColor: tooltipShadow,
			        backgroundColor: window.colors.solid.white,
			        titleFontColor: window.colors.solid.black,
			        bodyFontColor: window.colors.solid.black
			      },
			      responsive: true,
			  	  maintainAspectRatio: false,
			      responsiveAnimationDuration: 500,
			      legend: {
			        display: false
			      },
			      layout: {
			        padding: {
			          bottom: -30,
			          left: -25
			        }
				  },
					scales: {
						xAxes: [
						  {
						    display: true,
						    gridLines: {
						      zeroLineColor: grid_line_color,
						      borderColor: 'transparent',
						      color: grid_line_color
						    },
						    scaleLabel: {
						      display: true
						    },
						    ticks: {
						      min: 0,
						      fontColor: labelColor
						    }
						  }
						],
						yAxes: [
							{
						        display: true,
						        gridLines: {
									display: false
								},
								scaleLabel: {
									display: true
								},
								ticks: {
									fontColor: labelColor
								}
						  	}
						]
					}
			    },
			    data: {
			      labels: y,
			      datasets: [
				      {
				          data: x,
				       	  barThickness: 15,
				          backgroundColor: window.colors.solid.info,
				    	  borderColor: 'transparent'
				       }
			      ]
			    }
			  });
			}
		}
	});

  

});
