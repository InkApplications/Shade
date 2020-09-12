package inkapplications.shade.lights

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant

internal object HueUpdateStateTransformer {
    @ToJson
    fun toHueFormat(state: UpdateState): HueUpdateState = HueUpdateState(
        state = state.state,
        lastInstall = state.lastKnownInstall.takeIf { it != Instant.MIN }
    )

    @FromJson
    fun fromHueFormat(state: HueUpdateState): UpdateState = UpdateState(
        state = state.state,
        lastInstall = state.lastInstall ?: Instant.MIN,
        lastKnownInstall = state.lastInstall
    )
}
