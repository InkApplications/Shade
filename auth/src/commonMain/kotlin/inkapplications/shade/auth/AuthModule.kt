package inkapplications.shade.auth

import inkapplications.shade.internals.InternalsModule
import kimchi.logger.KimchiLogger

/**
 * Provides access to authorization services
 */
class AuthModule(
    internalsModule: InternalsModule,
    logger: KimchiLogger,
) {
    val bridgeAuth: BridgeAuth = ShadeBridgeAuth(
        client = internalsModule.hueHttpClient,
        logger = logger,
    )
}
