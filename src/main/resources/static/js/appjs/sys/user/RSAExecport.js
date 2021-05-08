
export var RSAUtil = {
    getRSAKeys: function () {
        var RSA = new JSEncrypt();
        var publicKey = RSA.getPublicBaseKeyB64();
        var privateKey = RSA.getPrivateBaseKeyB64();
        let obj = {
            publicKey,
            privateKey,
        };
        return obj;
    },

    encodeByRSA: function (str, publicKey) {
        let RSA = new JSEncrypt();
        RSA.setPublicKey(publicKey);
        return RSA.encrypt(str + '');
    },

    decodeByRSA: function (str, privateKey) {
        let RSA = new JSEncrypt();
        RSA.setPrivateKey(privateKey);
        return RSA.decrypt(str + '');
    },
    test: function () {},
};

export function encryptedData(publicKey, data) {
    // 新建JSEncrypt对象
    let encryptor = new JSEncrypt();
    // 设置公钥
    encryptor.setPublicKey(publicKey);
    // 加密数据
    return encryptor.encrypt(data);
}
