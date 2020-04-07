@extends('backend.master')
@section('content')
<section class="content-header">
    <h1>
        Dashboard
        <small>All Post</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="{{url('admin')}}"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">All Post</li>
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


    <div class="col-sm-12">
        <table class="table table-striped table-hover table-bordered">
            <thead>
                <tr>
                    <td>SN</td>
                    <td>Tittle</td>
                    <td>Featured Image</td>
                    <td>Category</td>
                    <td>Status</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                @foreach($data as $key=>$posts)
                <tr>
                    <td>{{++$key}}</td>
                    <td>{{$posts->title}}</td>
                    <td>
                        @if($posts->image)
                        <img src="{{url('public/contents')}}/{{$posts->image}}" width="50">
                        @endif
                    </td>
                    <td>{{$posts->category}}</td>
                    <td>{{$posts->status}}</td>
                    <td><a href="{{url('editPost')}}/{{$posts->conid}}" class="btn btn-sm btn-success"><i class="fa fa-edit"></i></a>
                    <a href="{{url('deletePost')}}/{{$posts->conid}}" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a></td>
                </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>
</section>

@stop