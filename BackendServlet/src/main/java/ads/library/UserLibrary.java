package ads.library;

import java.util.ArrayList;

import ads.objects.BookingObject;

public class UserLibrary {
	public static String viewUser(ArrayList<BookingObject> bookingObjects) {
		StringBuilder tmp = new StringBuilder("");

		tmp.append("""
				<table class="table datatable">
						              <thead>
						                <tr>
						                  <th style="width: 5%;">ID</th>
						                  <th style="width: 5%;">ID</th>
						                  <th style="width: 20%;">Note</th>
						                  <th style="width: 30%;">TourUnitId</th>
						                  <th style="width: 5%;">Tùy chọn</th>
						                </tr>
						              </thead>
						              <tbody>""");
		bookingObjects.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<td>").append(bookingObjects.indexOf(item)).append("</td>");
			tmp.append("<td>").append(bookingObjects.indexOf(item)).append("</td>");
			tmp.append("<td>").append(item.getNote()).append("</td>");
			tmp.append("<td>").append(item.getTour_unit_id()).append("</td>");

			tmp.append("""
					<td class="d-flex justify-content-evenly">
					                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
					                  data-bs-target="#updateFes"><i class="bi bi-pencil-fill"></i></button>
					                <button type="button" class="btn btn-danger" data-bs-toggle="modal"
					                  data-bs-target="#staticBackdrop"><i class="bi bi-trash3-fill"></i></button>
					              </td>
					""");
			tmp.append("</tr>");

		});
		tmp.append("""
	              </tbody>
	            </table>
""");
		return tmp.toString();
	}
}
