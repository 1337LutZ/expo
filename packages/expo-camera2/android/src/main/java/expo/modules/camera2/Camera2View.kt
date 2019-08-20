package expo.modules.camera2

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import expo.modules.camera2.camera.CameraRepository
import expo.modules.camera2.camera.ExpoCamera

import expo.modules.camera2.hardware.device.Device
import expo.modules.camera2.hardware.device.start
import expo.modules.camera2.hardware.device.switchCamera
import expo.modules.camera2.preview.PreviewTextureView

import expo.modules.camera2.settings.Autofocus
import expo.modules.camera2.settings.LensFacing
import expo.modules.camera2.settings.Flash
import expo.modules.camera2.settings.HDR
import expo.modules.camera2.settings.Mode
import expo.modules.camera2.settings.WhiteBalance
import java.util.concurrent.Executors
import kotlin.math.max

/**
 * Class that serves as entry point for any camera on device.
 * It holds preview and references to device and any camera sensor available.
 */
class Camera2View constructor(context: Context) :
    FrameLayout(context),
    Camera2 {
  private val expoCamera = ExpoCamera
  private val

  private val preview = PreviewTextureView(context)
  private val device = Device(
      context = context,
      previewTextureView = preview,
      )

  init {
    expoCamera.initialize(context)

    layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    addView(preview)
    startCamera()
    startPreview()
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    if (isInEditMode || preview.previewSize == null) {
      return super.onLayout(changed, left, top, right, bottom)
    }

    val resolution = preview.previewSize!!
    val scale = max(measuredWidth / resolution.width.toFloat(), measuredHeight / resolution.height.toFloat())

    val width = (resolution.width * scale).toInt()
    val height = (resolution.height * scale).toInt()

    val extraX = max(0, width - measuredWidth)
    val extraY = max(0, height - measuredHeight)

    (0 until childCount).map(::getChildAt).forEach {
      it.layout(
          -extraX / 2,
          -extraY / 2,
          width - extraX / 2,
          height - extraY / 2
      )
    }
  }

  // region Configuration

  override fun setLifecycleListener(listener: Camera2.LifecycleListener) {
//    cameraController.setCameraListener(listener)
  }

  override fun setAutofocus(autofocus: Autofocus) {
//    cameraController.settings.autofocus = autofocus
  }

  override fun setMode(mode: Mode) {
//    cameraController.settings.mode = mode
  }

  override fun setFocusDepth(focusDepth: Float) {
//    cameraController.settings.focusDepth = focusDepth
  }

  override fun setZoom(zoom: Float) {
//    cameraController.settings.zoom = zoom
  }

  override fun setWhiteBalance(whiteBalance: WhiteBalance) {
//    cameraController.settings.whiteBalance = whiteBalance
  }

  override fun setPictureSize(pictureSize: Float) {
//    cameraController.settings.pictureSize = pictureSize
  }

  override fun setFlash(flash: Flash) {
//    cameraController.settings.flash = flash
  }

  override fun setHDR(hdr: HDR) {
//    cameraController.settings.hdr = hdr
  }

  override fun setFacing(facing: LensFacing) {
//    cameraController.settings.facing = facing
  }

  fun switchFacing() {

    device.switchCamera(device.getFacing())
  }

  // endregion


  // region Lifecycle

  override fun startCamera() {
//    TODO("check permissions")
    executor.execute {
      device.start()
    }
  }

  override fun stopCamera() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun startPreview() {

  }

  override fun pausePreview() {

  }

  override fun resumePreview() {

  }

  override fun stopPreview() {

  }

  override fun restartPreview() {

  }

  // endregion


  // region Functionalities

  override fun takePicture() {
    TODO()
  }

  override fun record() {
    TODO()
  }

  override fun stopRecording() {
    TODO()
  }

  override fun getAvailablePictureSizes() {
    TODO()
  }

  override fun focusAt(x: Float, y: Float) {
    TODO()
  }

  // endregion
}
