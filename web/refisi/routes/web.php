<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', 'frontController@index');

Route::get('admin', 'adminController@admin');

Route::get('setups', 'adminController@setups');

Route::post('addSettings', 'crudController@insertData');

Route::get('categories', 'adminController@categories');

Route::post('addCategory', 'crudController@insertData');

Route::get('deleteCategory/{id}', 'adminController@deleteCategory');

Route::get('editCategory/{id}', 'adminController@editCategory');

Route::post('updateCategory/{id}', 'crudController@updateData');

Route::get('new-post','adminController@newPost');

Route::post('addPost', 'crudController@insertData');

Route::get('all-post', 'adminController@allPosts');

Route::get('editPost/{id}', 'adminController@editPosts');

Route::post('updatePost/{id}', 'crudController@updateData');

Route::get('deletePost/{id}', 'adminController@deletePost');

Route::get('new-service','adminController@newService');

Route::post('addService', 'crudController@insertData');

Route::get('all-services', 'adminController@allServices');

Route::get('editService/{id}', 'adminController@editService');

Route::post('updateService/{id}', 'crudController@updateData');

Route::get('deleteService/{id}', 'adminController@deleteService');

Route::get('portcats', 'adminController@portcats');

Route::post('addPc', 'crudController@insertData');

Route::get('new-portfolio', 'adminController@portfolio');

Route::post('addPortfolio', 'crudController@insertData');

Route::get('clients', 'adminController@clients');

Route::post('addClient', 'crudController@insertData');

Route::get('new-member', 'adminController@newMember');

Route::post('addMember', 'crudController@insertData');










