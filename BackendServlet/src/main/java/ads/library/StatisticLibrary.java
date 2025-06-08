package ads.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableBoolean;

import ads.objects.BookingAggregate;
import ads.user.StatisticFunctionImpl;
import ads.util.GsonProvider;
import ads.util.SchedulerUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatisticLibrary {

	public static String getStackedBarChart() {
		StringBuilder sBuilder = new StringBuilder();

//		sBuilder.append("<div class=\"card\">");
//		sBuilder.append("<div class=\"card-body\">");
//		sBuilder.append("<div class=\"row\">");
//		sBuilder.append("<div class=\"col pe-0\">");
//		sBuilder.append("<h5 class=\"card-title\">Thống kê số lượng khách theo tháng và độ tuổi</h5>");
//		sBuilder.append("</div>");
//		sBuilder.append("<div class=\"col-auto mt-3\">");
//		sBuilder.append("<select class=\"form-select\">");
//		sBuilder.append("<option value=\"2025\" selected>2025</option>");
//		sBuilder.append("<option value=\"2024\">2024</option>");
//		sBuilder.append("</select>");
//		sBuilder.append("</div>");
//		sBuilder.append("</div>");
//		sBuilder.append("<!-- Stacked Bar Chart -->");
//		sBuilder.append("<canvas id=\"stakedBarChart\" style=\"max-height: 400px;\"></canvas>");
//		sBuilder.append("<script>");
//		sBuilder.append("document.addEventListener(\"DOMContentLoaded\", () => {");
//		sBuilder.append("const chartDataByYear = {");
//		sBuilder.append("\"2024\": {");
//		sBuilder.append("labels: ['Th.1', 'Th.2', 'Th.3', 'Th.4', 'Th.5', 'Th.6', 'Th.7', 'Th.8', 'Th.9', 'Th.10', 'Th.11', 'Th.12'],");
//		sBuilder.append("datasets: [");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Em bé',");
//		sBuilder.append("data: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120],");
//		sBuilder.append("backgroundColor: 'rgb(255, 99, 132)'");
//		sBuilder.append(")},");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Trẻ nhỏ',");
//		sBuilder.append("data: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60],");
//		sBuilder.append("backgroundColor: 'rgb(75, 192, 192)'");
//		sBuilder.append(")},");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Trẻ em',");
//		sBuilder.append("data: [3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36],");
//		sBuilder.append("backgroundColor: 'rgb(255, 205, 86)'");
//		sBuilder.append(")},");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Người lớn',");
//		sBuilder.append("data: [20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130],");
//		sBuilder.append("backgroundColor: 'rgb(37, 150, 190)'");
//		sBuilder.append(")}");
//		sBuilder.append(")]");
//		sBuilder.append(")},");
//		sBuilder.append("\"2025\": {");
//		sBuilder.append("labels: ['Th.1', 'Th.2', 'Th.3', 'Th.4', 'Th.5', 'Th.6', 'Th.7', 'Th.8', 'Th.9', 'Th.10', 'Th.11', 'Th.12'],");
//		sBuilder.append("datasets: [");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Em bé',");
//		sBuilder.append("data: [-75, -15, 18, 48, 74, 0, 0, 0, 0, 0, 0, 0],");
//		sBuilder.append("backgroundColor: 'rgb(255, 99, 132)'");
//		sBuilder.append(")},");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Trẻ nhỏ',");
//		sBuilder.append("data: [-11, -1, 12, 62, 95, 0, 0, 0, 0, 0, 0, 0],");
//		sBuilder.append("backgroundColor: 'rgb(75, 192, 192)'");
//		sBuilder.append(")},");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Trẻ em',");
//		sBuilder.append("data: [-44, -5, 22, 35, 62, 0, 0, 0, 0, 0, 0, 0],");
//		sBuilder.append("backgroundColor: 'rgb(255, 205, 86)'");
//		sBuilder.append(")},");
//		sBuilder.append("{");
//		sBuilder.append("label: 'Người lớn',");
//		sBuilder.append("data: [-44, -5, 22, 35, 62, 0, 0, 0, 0, 0, 0, 0],");
//		sBuilder.append("backgroundColor: 'rgb(37, 150, 190)'");
//		sBuilder.append(")}");
//		sBuilder.append(")]");
//		sBuilder.append(")}");
//		sBuilder.append(")};");
//		sBuilder.append("let stackedBarChart;");
//		sBuilder.append("const ctx = document.querySelector('#stakedBarChart');");
//		sBuilder.append("const initialYear = \"2025\";");
//		sBuilder.append("const chartConfig = getChartConfig(chartDataByYear[initialYear]);");
//		sBuilder.append("stackedBarChart = new Chart(ctx, chartConfig);");
//		sBuilder.append("document.querySelector('select.form-select').addEventListener('change', (event) => {");
//		sBuilder.append("const selectedYear = event.target.value;");
//		sBuilder.append("updateChartData(stackedBarChart, chartDataByYear[selectedYear]);");
//		sBuilder.append(")});");
//		sBuilder.append("function getChartConfig(data) {");
//		sBuilder.append("return {");
//		sBuilder.append("type: 'bar',");
//		sBuilder.append("data: {");
//		sBuilder.append("labels: data.labels,");
//		sBuilder.append("datasets: data.datasets");
//		sBuilder.append(")},");
//		sBuilder.append("options: {");
//		sBuilder.append("plugins: {");
//		sBuilder.append("title: {");
//		sBuilder.append("display: false,");
//		sBuilder.append("text: 'Chart.js Bar Chart - Stacked'");
//		sBuilder.append(")},");
//		sBuilder.append(")},");
//		sBuilder.append("responsive: true,");
//		sBuilder.append("scales: {");
//		sBuilder.append("x: {");
//		sBuilder.append("stacked: true,");
//		sBuilder.append(")},");
//		sBuilder.append("y: {");
//		sBuilder.append("stacked: true");
//		sBuilder.append(")}");
//		sBuilder.append(")}");
//		sBuilder.append(")}");
//		sBuilder.append(")};");
//		sBuilder.append(")}");
//		sBuilder.append("function updateChartData(chart, newData) {");
//		sBuilder.append("chart.data.labels = newData.labels;");
//		sBuilder.append("chart.data.datasets = newData.datasets;");
//		sBuilder.append("chart.update();");
//		sBuilder.append(")}");
//		sBuilder.append(")})");
//		sBuilder.append("</script>");
//		sBuilder.append("<!-- End Stacked Bar Chart -->");
//		sBuilder.append("</div>");
//		sBuilder.append("</div>");

		//List<BookingAggregate> result = StatisticFunctionImpl.getInstance().getStackedBarChartData();
		List<BookingAggregate> result = SchedulerUtil.getStackedBarChartData();
		// result.forEach(item -> System.out.println(item));

		String chartJson = generateChartData(result);
		// log.info("CHART JSON: {}", chartJson);

		Set<Integer> year = result.stream().map(BookingAggregate::getYear).collect(Collectors.toSet());
		
		sBuilder.append("""
				  <div class="card">
				<div class="card-body">
				  <div class="row">
				    <div class="col pe-0">
				      <h5 class="card-title mb-0 pb-0">Thống kê số lượng khách theo tháng và độ tuổi</h5>
				    </div>
				    <div class="col-12 col-md-auto col-lg-12 mt-3">
				      <select class="form-select">
				    """);

		Mutable<Boolean> flag = new MutableBoolean(true);
		
		year.forEach(y -> {
			sBuilder.append("<option value=\"").append(y).append("\" ").append(flag.getValue()?"selected":"").append(">").append(y).append("</option>");
			if (flag.equals(true)) {
				flag.setValue(false);
			}
		});
		
		sBuilder.append(
				"""
						      <!--<option value="2025" selected>2025</option>
						      <option value="2024">2024</option>-->
						    </select>
						  </div>
						</div>

						<!-- Stacked Bar Chart -->
						<canvas id="stakedBarChart" style="min-height: 300px; max-height: 600px;" class="mt-xxl-2 mb-xxl-1 my-xl-3 my-4"></canvas>
						<script>
						  document.addEventListener("DOMContentLoaded", () => {
						  const chartDataByYear =
						  """);

		sBuilder.append(chartJson).append(";");
//		{
//            "2024": {
//              labels: ['Th.1', 'Th.2', 'Th.3', 'Th.4', 'Th.5', 'Th.6', 'Th.7', 'Th.8', 'Th.9', 'Th.10', 'Th.11', 'Th.12'],
//              datasets: [
//                {
//                  label: 'Em bé',
//                  data: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120],
//                  backgroundColor: 'rgb(255, 99, 132)'
//                },
//                {
//                  label: 'Trẻ nhỏ',
//                  data: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60],
//                  backgroundColor: 'rgb(75, 192, 192)'
//                },
//                {
//                  label: 'Trẻ em',
//                  data: [3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36],
//                  backgroundColor: 'rgb(255, 205, 86)'
//                },
//                {
//                  label: 'Người lớn',
//                  data: [20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130],
//                  backgroundColor: 'rgb(37, 150, 190)'
//                }
//              ]
//            },
//            "2025": {
//              labels: ['Th.1', 'Th.2', 'Th.3', 'Th.4', 'Th.5', 'Th.6', 'Th.7', 'Th.8', 'Th.9', 'Th.10', 'Th.11', 'Th.12'],
//              datasets: [
//                {
//                  label: 'Em bé',
//                  data: [-75, -15, 18, 48, 74, 0, 0, 0, 0, 0, 0, 0],
//                  backgroundColor: 'rgb(255, 99, 132)'
//                },
//                {
//                  label: 'Trẻ nhỏ',
//                  data: [-11, -1, 12, 62, 95, 0, 0, 0, 0, 0, 0, 0],
//                  backgroundColor: 'rgb(75, 192, 192)'
//                },
//                {
//                  label: 'Trẻ em',
//                  data: [-44, -5, 22, 35, 62, 0, 0, 0, 0, 0, 0, 0],
//                  backgroundColor: 'rgb(255, 205, 86)'
//                },
//                {
//                  label: 'Người lớn',
//                  data: [-44, -5, 22, 35, 62, 0, 0, 0, 0, 0, 0, 0],
//                  backgroundColor: 'rgb(37, 150, 190)'
//                }
//              ]
//            }
//          };
		sBuilder.append("""

				              let stackedBarChart;

				              const ctx = document.querySelector('#stakedBarChart');
				              const initialYear = "2025";
				              const chartConfig = getChartConfig(chartDataByYear[initialYear]);
				              stackedBarChart = new Chart(ctx, chartConfig);

				              document.querySelector('select.form-select').addEventListener('change', (event) => {
				                const selectedYear = event.target.value;
				                updateChartData(stackedBarChart, chartDataByYear[selectedYear]);
				              });

				              function getChartConfig(data) {
				                return {
				                  type: 'bar',
				                  data: {
				                    labels: data.labels,
				                    datasets: data.datasets
				                  },
				                  options: {
				                    plugins: {
				                      title: {
				                        display: false,
				                        text: 'Chart.js Bar Chart - Stacked'
				                      },
				                    },
				                    responsive: true,
				                    scales: {
				                      x: {
				                        stacked: true,
				                      },
				                      y: {
				                        stacked: true
				                      }
				                    }
				                  }
				                };
				              }

				              function updateChartData(chart, newData) {
				                chart.data.labels = newData.labels;
				                chart.data.datasets = newData.datasets;
				                chart.update();
				              }
				            })
				          </script>
				          <!-- End Stacked Bar Chart -->

				        </div>
				      </div>
				""");

		return sBuilder.toString();
	}

	public static String generateChartData(List<BookingAggregate> dataList) {
		Map<String, String> colorMap = Map.of("Người lớn", "rgb(37, 150, 190)", "Trẻ em", "rgb(255, 205, 86)",
				"Trẻ nhỏ", "rgb(75, 192, 192)", "Em bé", "rgb(255, 99, 132)");

		// Nhóm theo năm
		Map<Integer, List<BookingAggregate>> dataByYear = dataList.stream()
				.collect(Collectors.groupingBy(b -> b.getYear()));

		Map<String, Object> chartDataByYear = new LinkedHashMap<>();

		for (Map.Entry<Integer, List<BookingAggregate>> yearEntry : dataByYear.entrySet()) {
			int year = yearEntry.getKey();
			List<BookingAggregate> yearData = yearEntry.getValue();

			// Tạo map theo tháng
			Map<Integer, BookingAggregate> monthMap = yearData.stream()
					.collect(Collectors.toMap(b -> b.getMonth(), b -> b, (a, b) -> b // nếu trùng thì lấy cái sau
					));

			// Điền đủ 12 tháng
			List<BookingAggregate> fullYearData = new ArrayList<>();
			for (int m = 1; m <= 12; m++) {
				if (monthMap.containsKey(m)) {
					fullYearData.add(monthMap.get(m));
				} else {
					fullYearData.add(new BookingAggregate(year, m, 0, 0, 0, 0));
				}
			}

			// labels
			List<String> labels = IntStream.rangeClosed(1, 12).mapToObj(i -> "Th." + i).collect(Collectors.toList());

			// Tạo từng dataset theo loại người
			int[] adult = new int[12];
			int[] child = new int[12];
			int[] toddler = new int[12];
			int[] baby = new int[12];

			for (int i = 0; i < 12; i++) {
				BookingAggregate b = fullYearData.get(i);
				adult[i] = b.getAdultQuants();
				child[i] = b.getChildQuants();
				toddler[i] = b.getToddlerQuants();
				baby[i] = b.getBabyQuants();
			}

			List<Map<String, Object>> datasets = new ArrayList<>();

			datasets.add(createDataset("Người lớn", adult, colorMap));
			datasets.add(createDataset("Trẻ em", child, colorMap));
			datasets.add(createDataset("Trẻ nhỏ", toddler, colorMap));
			datasets.add(createDataset("Em bé", baby, colorMap));

			Map<String, Object> yearChart = new LinkedHashMap<>();
			yearChart.put("labels", labels);
			yearChart.put("datasets", datasets);

			chartDataByYear.put(String.valueOf(year), yearChart);
		}

		return GsonProvider.getGson().toJson(chartDataByYear);
	}

	private static Map<String, Object> createDataset(String label, int[] data, Map<String, String> colorMap) {
		Map<String, Object> dataset = new LinkedHashMap<>();
		dataset.put("label", label);
		dataset.put("data", Arrays.stream(data).boxed().collect(Collectors.toList()));
		dataset.put("backgroundColor", colorMap.getOrDefault(label, "#ccc"));
		return dataset;
	}
}