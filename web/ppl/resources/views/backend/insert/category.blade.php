@extends('backend.master')
@section('content')
<section class="content-header">
    <h1>
        Dashboard
        <small>Categories</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="{{url('admin')}}"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"> Categories</li>
    </ol>
</section>


<section class="content">
<div class="row">
    @if(Session::has('message'))
    <div class="col-sm-12">
        <div class="alert alert-success alert-dismissable">
            {{ session::get('message') }}
            <a class="close" data-dismiss="alert">&times;</a>
        </div>
    </div>
    @endif

<div class="col-sm-6">
    <form method="post" action="{{url('addCategory')}}">
    {{ csrf_field() }}
    <input type="hidden" name="tbl" value="{{encrypt('categories')}}">
        <div class="form-group">
            <label>Title</label>
            <input type="text" name="title" class="form-control">
        </div>
        <div class="form-group">
            <label>Status</label>
            <select class="form-control" name="status">
                <option>on</option>
                <option>off</option>
        </select>
    </div>    
        <div class="form-group">
            <button class="btn btn-success">Add category</button>
        </div>
    </form>
    </div>
    <div class="col-sm-6">
        <p><strong>View All Categories</strong></p>
        <table class="table table-striped table-hover table-bordered">
            <thead>
                <tr>
                    <td>SN</td>
                    <td>Tittle</td>
                    <td>Status</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                @foreach($data as $key=>$category)
                <tr>
                    <td>{{++$key}}</td>
                    <td>{{$category->title}}</td>
                    <td>{{$category->status}}</td>
                    <td><a href="{{url('editCategory')}}/{{$category->cid}}" class="btn btn-sm btn-success"><i class="fa fa-edit"></i></a>
                    <a href="{{url('deleteCategory')}}/{{$category->cid}}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a></td>
                </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
</section>

@stop