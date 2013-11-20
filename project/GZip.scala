import sbt._
import Keys._
import play.Project._

object GZip {
  val gzippableAssets = SettingKey[PathFinder]("gzippable-assets")

  val gzipAssets = TaskKey[Seq[File]]("gzip-assets")

  lazy val gzipAssetsSetting = gzipAssets <<= gzipAssetsTask dependsOn (copyResources in Compile)

  lazy val gzipAssetsTask = (gzippableAssets, streams) map {
    case (finder: PathFinder, s: TaskStreams) => {
      val files = finder.get.map {
        file =>
          val target = new File(s"${file.getAbsolutePath}.gz")
          IO.gzip(file, target)
          target
      }
      s.log.info(s"Compressed ${files.size} asset(s)")
      files
    }
  }
  val settings = Seq(
    gzippableAssets <<= (classDirectory in Compile)(dir => dir ** ("*.js" || "*.css" || "*.html")),
    gzipAssetsSetting,
    (packageBin in Compile) <<= (packageBin in Compile) dependsOn gzipAssets
  )
}