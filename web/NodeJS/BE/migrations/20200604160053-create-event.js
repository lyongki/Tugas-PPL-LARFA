'use strict';
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.createTable('events', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      nama_event: {
        type: Sequelize.STRING
      },
      tanggal: {
        type: Sequelize.DATE
      },
      thumbnail: {
        type: Sequelize.STRING
      },
      deskripsi: {
        type: Sequelize.STRING
      },
      nama_ukm: {
        type: Sequelize.STRING
      },
      evaluasi: {
        type: Sequelize.TEXT('long')
      }
    });
  },
  down: (queryInterface, Sequelize) => {
    return queryInterface.dropTable('events');
  }
};