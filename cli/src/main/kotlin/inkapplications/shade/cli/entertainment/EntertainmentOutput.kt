package inkapplications.shade.cli.entertainment

import com.github.ajalt.clikt.core.CliktCommand
import inkapplications.shade.entertainment.structures.Entertainment

fun CliktCommand.echoEntertainment(entertainment: Entertainment) {
    echo("${entertainment.id.value}:")
    echo("    Owner: ${entertainment.owner}")
    echo("    Renderer: ${entertainment.renderer}")
    val renderReference = entertainment.rendererReference
    if (renderReference != null) {
        echo("    Renderer Reference: $renderReference")
    }
    echo("    Proxy: ${entertainment.proxy}")
    echo("    Equalizer: ${entertainment.equalizer}")
    val maxStreams = entertainment.maxStreams
    if (maxStreams != null) {
        echo("    Max Streams: $maxStreams")
    }
    entertainment.segments?.let { segments ->
        echo("    Segments:")
        echo("        Configurable: ${segments.configurable}")
        echo("        Max Segments: ${segments.maxSegments}")
        echo("        Ranges:")
        segments.segmentRanges.forEach { range ->
            echo("             - ${range.first}:${range.last}")
        }
    }
}
