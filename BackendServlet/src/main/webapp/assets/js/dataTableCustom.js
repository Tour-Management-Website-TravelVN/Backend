// Gen STT
document.addEventListener("DOMContentLoaded", function () {
  const rows = document.querySelectorAll("table tbody tr");
  rows.forEach((row, index) => {
    const sttCell = row.querySelector("th");
    sttCell.textContent = index + 1;
  });
});

// Phân trang Table
const table = document.getElementById("myTable");
const thead = table.querySelector("thead");
const tbody = table.querySelector("tbody");
const searchInput = document.getElementById("searchInput");
const recordCount = document.getElementById("recordCount");
const recordInfo = document.getElementById("recordInfo");
const pagination = document.getElementById("pagination");

let rows = Array.from(tbody.querySelectorAll("tr"));
let filteredRows = [...rows];
let currentPage = 1;
let rowsPerPage = parseInt(recordCount.value);
let sortColumnIndex = -1;
let sortAscending = true;

function renderTable() {
  tbody.innerHTML = "";
  let start = (currentPage - 1) * rowsPerPage;
  let end = start + rowsPerPage;
  let pageRows = filteredRows.slice(start, end);

  pageRows.forEach((row) => {
    tbody.appendChild(row);
  });

  const totalRecords = filteredRows.length;
  const showingStart = totalRecords === 0 ? 0 : start + 1;
  const showingEnd = end > totalRecords ? totalRecords : end;
  recordInfo.textContent = `Hiển thị ${showingStart} đến ${showingEnd} trong số ${totalRecords} bản ghi`;

  renderPagination();
}

/*
function renderPagination() {
  pagination.innerHTML = "";
  let totalPages = Math.ceil(filteredRows.length / rowsPerPage);

  if (totalPages <= 1) return;

  const createPageButton = (text, page) => {
    const btn = document.createElement("button");
    btn.textContent = text;
    btn.className = "btn btn-outline-secondary btn-sm mx-1 ps-3 pe-3";
    btn.disabled = page === currentPage;
    btn.onclick = () => {
      currentPage = page;
      renderTable();
    };
    return btn;
  };

  if (currentPage > 1) {
    pagination.appendChild(createPageButton("«", currentPage - 1));
  }

  for (let i = 1; i <= totalPages; i++) {
    pagination.appendChild(createPageButton(i, i));
  }

  if (currentPage < totalPages) {
    pagination.appendChild(createPageButton("»", currentPage + 1));
  }
}
*/

function renderPagination() {
  pagination.innerHTML = "";
  const totalPages = Math.ceil(filteredRows.length / rowsPerPage);
  if (totalPages <= 1) return;

  const maxButtonsToShow = 5; // Số lượng nút hiển thị gần currentPage
  const createPageButton = (text, page, disabled = false) => {
    const btn = document.createElement("button");
    btn.textContent = text;
    btn.className = "btn btn-outline-secondary btn-sm mx-1 ps-3 pe-3";
    btn.disabled = disabled || page === currentPage;
    btn.onclick = () => {
      currentPage = page;
      renderTable();
    };
    return btn;
  };

  const createEllipsis = () => {
    const span = document.createElement("span");
    span.textContent = "...";
    span.className = "mx-1 text-muted";
    return span;
  };

  // Nút «
  if (currentPage > 1) {
    pagination.appendChild(createPageButton("«", currentPage - 1));
  }

  // Luôn hiển thị nút trang 1
  pagination.appendChild(createPageButton("1", 1));

  let startPage = Math.max(2, currentPage - 1);
  let endPage = Math.min(totalPages - 1, currentPage + 1);

  if (currentPage <= 3) {
    endPage = Math.min(totalPages - 1, 4);
  } else if (currentPage >= totalPages - 2) {
    startPage = Math.max(2, totalPages - 3);
  }

  // Dấu ...
  if (startPage > 2) {
    pagination.appendChild(createEllipsis());
  }

  for (let i = startPage; i <= endPage; i++) {
    pagination.appendChild(createPageButton(i, i));
  }

  if (endPage < totalPages - 1) {
    pagination.appendChild(createEllipsis());
  }

  // Luôn hiển thị nút trang cuối
  if (totalPages > 1) {
    pagination.appendChild(createPageButton(totalPages, totalPages));
  }

  // Nút »
  if (currentPage < totalPages) {
    pagination.appendChild(createPageButton("»", currentPage + 1));
  }
}



recordCount.addEventListener("change", () => {
  const selectedValue = recordCount.value;
  if (selectedValue === "all") {
    rowsPerPage = filteredRows.length || 1;
  } else {
    rowsPerPage = parseInt(selectedValue);
  }
  currentPage = 1;
  renderTable();
});

searchInput.addEventListener("input", () => {
  const keyword = searchInput.value.trim().toLowerCase();
  filteredRows = rows.filter((row) => {
    return Array.from(row.cells).some((cell) =>
      cell.textContent.toLowerCase().includes(keyword)
    );
  });
  currentPage = 1;
  renderTable();
});

thead.addEventListener("click", function (e) {
  const th = e.target.closest("th");
  if (!th) return;
  const ths = Array.from(thead.querySelectorAll("th"));
  const index = ths.indexOf(th);

  if (index < 0 || index === 0 || index === ths.length - 1) return;

  if (sortColumnIndex === index) {
    sortAscending = !sortAscending;
  } else {
    sortColumnIndex = index;
    sortAscending = true;
  }

  filteredRows.sort((rowA, rowB) => {
    const cellA = rowA.cells[index].textContent.trim().toLowerCase();
    const cellB = rowB.cells[index].textContent.trim().toLowerCase();

    if (!isNaN(cellA) && !isNaN(cellB)) {
      return sortAscending ? cellA - cellB : cellB - cellA;
    }

    return sortAscending
      ? cellA.localeCompare(cellB, "vi", { sensitivity: "base" })
      : cellB.localeCompare(cellA, "vi", { sensitivity: "base" });
  });

  updateSortIndicators();
  currentPage = 1;
  renderTable();
});

function updateSortIndicators() {
  const ths = thead.querySelectorAll("th");
  ths.forEach((th, idx) => {
    const text = th.getAttribute("data-title") || th.textContent.trim();
    th.setAttribute("data-title", text);
    th.innerHTML = text;
    if (idx === sortColumnIndex) {
      th.innerHTML += sortAscending
        ? ' <i class="bi bi-caret-up-fill"></i>'
        : ' <i class="bi bi-caret-down-fill"></i>';
    }
  });
}

window.addEventListener("load", () => {
  renderTable();
});
