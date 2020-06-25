'use strict';
module.exports = (sequelize, DataTypes) => {
  const inventaris = sequelize.define('inventaris', {
    nama: DataTypes.STRING,
    jumlah: DataTypes.INTEGER,
    role: DataTypes.INTEGER,
    jumlah_dipinjam: DataTypes.INTEGER
  }, {
    timestamps: false,
  });
  inventaris.associate = function(models) {
    // associations can be defined here
  };
  return inventaris;
};