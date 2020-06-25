'use strict';
const faker = require('faker')
module.exports = {
  up: (queryInterface, Sequelize) => {
      return queryInterface.bulkInsert('events', 
        Array.from({length:10}).map(()=>({
          nama_event:faker.lorem.sentence(),
          tanggal:faker.date.recent(),
          thumbnail:faker.image.imageUrl(),
          deskripsi:faker.lorem.sentence(),
          nama_ukm:faker.name.jobType(),
          evaluasi:faker.lorem.paragraph()
        })),
      {});
  },

  down: (queryInterface, Sequelize) => {
    /*
      Add reverting commands here.
      Return a promise to correctly handle asynchronicity.

      Example:
      return queryInterface.bulkDelete('People', null, {});
    */
  }
};
