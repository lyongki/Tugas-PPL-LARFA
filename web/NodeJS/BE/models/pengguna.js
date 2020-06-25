'use strict';
module.exports = (sequelize, DataTypes) => {
  const pengguna = sequelize.define('pengguna', {
    nama: DataTypes.STRING,
    username: DataTypes.STRING,
    password: DataTypes.STRING,
    role: DataTypes.INTEGER
  }, {
    timestamps: false,
  });
  pengguna.associate = function(models) {
    // associations can be defined here
  };
  return pengguna;
};