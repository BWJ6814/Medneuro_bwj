/**
 * ✅ dragdrop.js
 * - 드래그앤드롭 설정만 분리 (원본 로직 그대로)
 */

export function setupDragDrop(onFilePicked) {
  const $dz = $("#dropzone");

  $dz.on("dragover", function (e) {
    e.preventDefault();
    $dz.addClass("dragover");
  });

  $dz.on("dragleave", function () {
    $dz.removeClass("dragover");
  });

  $dz.on("drop", function (e) {
    e.preventDefault();
    $dz.removeClass("dragover");

    const file = e.originalEvent.dataTransfer.files?.[0];
    if (!file) return;

    onFilePicked(file);
  });
}
