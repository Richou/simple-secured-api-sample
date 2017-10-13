package com.heanoria.reminders.simplesecuredapisample.security

import com.heanoria.reminders.simplesecuredapisample.configuration.properties.KeyPairProperties
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.security.KeyFactory
import java.security.KeyPair
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec


class KeyPairHandler(val keyPairProperties: KeyPairProperties) {

    private val logger = LoggerFactory.getLogger(KeyPairHandler::class.java)

    fun loadKeyPair() : KeyPair? {
        // Read Public Key.
        val filePublicKey = File(keyPairProperties.publicKey)
        var fis = FileInputStream(keyPairProperties.publicKey)
        val encodedPublicKey = ByteArray(filePublicKey.length().toInt())
        fis.read(encodedPublicKey)
        fis.close()

        if (logger.isDebugEnabled) {
            logger.debug("Trying to read private key : " + keyPairProperties.privateKey)
        }
        // Read Private Key.
        val filePrivateKey = File(keyPairProperties.privateKey)
        fis = FileInputStream(keyPairProperties.privateKey)
        val encodedPrivateKey = ByteArray(filePrivateKey.length().toInt())
        fis.read(encodedPrivateKey)
        fis.close()

        if (logger.isDebugEnabled) {
            logger.debug("Trying to generate keypair")
        }
        // Generate KeyPair.
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKeySpec = X509EncodedKeySpec(encodedPublicKey)
        val publicKey = keyFactory.generatePublic(publicKeySpec)

        if (logger.isDebugEnabled) {
            logger.debug("Public key generated")
        }

        val privateKeySpec = PKCS8EncodedKeySpec(encodedPrivateKey)
        val privateKey = keyFactory.generatePrivate(privateKeySpec)

        if (logger.isDebugEnabled) {
            logger.debug("Private key generated")
        }

        return KeyPair(publicKey, privateKey)
    }
}