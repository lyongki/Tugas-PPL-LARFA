<?php
    require_once 'koneksi_ServiceTugasPPL.php';
    global $connection;

    $flag = $_POST['flag'];
	date_default_timezone_set('Asia/Bangkok');
    
    if($flag == "get_all_event"){
        $query_event = "SELECT * FROM event";
        $event = mysqli_query($connection,$query_event);
        if($event){
            $tempArray = array();
            $resultArray = array();
            while($row = $event->fetch_object()){
                $query_ukm = "SELECT * FROM ukm WHERE id ='".$row->id_ukm."'";
				$ukm = mysqli_query($connection,$query_ukm);
				$hasil_ukm = mysqli_fetch_array($ukm);
				$tempArray = [
                    'id' => $row->id,
                    'nama_event' => $row->nama_event,
                    'tanggal' => $row->tanggal,
                    'thumbnail' => $row->thumbnail,
                    'deskripsi' => $row->deskripsi,
                    'id_ukm' => $row->id_ukm,
                    'nama_ukm' => $hasil_ukm['nama_ukm']
                ];
                array_push($resultArray, $tempArray);
            }
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }

    if($flag == "get_ukm"){
        $query_event = "SELECT * FROM ukm";
        $event = mysqli_query($connection,$query_event);
        if($event){
            $tempArray = array();
            $resultArray = array();
            while($row = $event->fetch_object()){
                $tempArray = [
					'id' => $row->id,
					'nama_ukm' => $row->nama_ukm
				];
                array_push($resultArray, $tempArray);
            }
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }

    if($flag == "get_ukm_all_event"){
        $id_ukm = $_POST['id_ukm'];
        $query_event = "SELECT * FROM event WHERE id_ukm = '".$id_ukm."'";
        $event = mysqli_query($connection,$query_event);
        if($event){
            $tempArray = array();
            $resultArray = array();
            while($row = $event->fetch_object()){
                $query_ukm = "SELECT nama_ukm FROM ukm WHERE id ='".$row->id_ukm."'";
				$ukm = mysqli_query($connection,$query_ukm);
				$hasil_ukm = mysqli_fetch_array($ukm);
				$tempArray = [
                    'id' => $row->id,
                    'nama_event' => $row->nama_event,
                    'tanggal' => $row->tanggal,
                    'thumbnail' => $row->thumbnail,
                    'deskripsi' => $row->deskripsi,
                    'id_ukm' => $row->id_ukm,
                    'nama_ukm' => $hasil_ukm['nama_ukm']
                ];
                array_push($resultArray, $tempArray);
            }
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
	
	if($flag == "login_pengurus"){
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		$query_user = "SELECT * FROM pengguna WHERE username = '".$username."' AND password = '".$password."'";
        $user = mysqli_query($connection,$query_user);
		$hasil_user = mysqli_fetch_array($user);
		
		if($hasil_user ['id'] !=null){
			$resultArray = [
				'code' => '1',
				'id' => $hasil_user['id'],
				'nama' => $hasil_user['nama'],
				'id_ukm' => $hasil_user['id_ukm'],
				'role' => $hasil_user['role']
			];
			
		}else{
			$resultArray = [
				'code' => 0
			];
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
	}
	
	if($flag == "get_surat"){
        $id_ukm = $_POST['id_ukm'];
        $tipe = $_POST['tipe'];
        if($tipe == "2")
			$query_surat = "SELECT * FROM surat WHERE id_ukm = '".$id_ukm."'";
		else
			$query_surat = "SELECT * FROM surat WHERE role = '".$tipe."' AND id_ukm = '".$id_ukm."'";
		$surat = mysqli_query($connection,$query_surat);
        if($surat){
            $tempArray = array();
            $resultArray = array();
			while($row = $surat->fetch_object()){
				$tempArray = [
					'id' => $row->id,
					'tanggal' => $row->tanggal,
					'role' => $row->role,
					'nama' => $row->nama,
					'file' => $row->file
				];
				array_push($resultArray, $tempArray);
			}
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "search_surat"){
        $id_ukm = $_POST['id_ukm'];
        $tipe = $_POST['tipe'];
        $key = $_POST['key'];
        if($tipe == "2")
			$query_surat = "SELECT * FROM surat WHERE id_ukm = '".$id_ukm."' AND nama LIKE '%".$key."%'";
		else
			$query_surat = "SELECT * FROM surat WHERE role = '".$tipe."' AND id_ukm = '".$id_ukm."' AND nama LIKE '%".$key."%'";
		$surat = mysqli_query($connection,$query_surat);
        if($surat){
            $tempArray = array();
            $resultArray = array();
			while($row = $surat->fetch_object()){
				$tempArray = [
					'id' => $row->id,
					'tanggal' => $row->tanggal,
					'role' => $row->role,
					'nama' => $row->nama,
					'file' => $row->file
				];
				array_push($resultArray, $tempArray);
			}
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "get_rapat"){
        $id_ukm = $_POST['id_ukm'];
        
		$query_surat = "SELECT * FROM rapat WHERE id_ukm = '".$id_ukm."'";
		$surat = mysqli_query($connection,$query_surat);
        if($surat){
            $tempArray = array();
            $resultArray = array();
			while($row = $surat->fetch_object()){
				$tempArray = [
					'id' => $row->id,
					'tanggal' => $row->tanggal,
					'nama' => $row->nama,
					'hasil' => $row->hasil
				];
				array_push($resultArray, $tempArray);
			}
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "search_rapat"){
        $id_ukm = $_POST['id_ukm'];      
        $key = $_POST['key'];
        
		$query_surat = "SELECT * FROM rapat WHERE nama LIKE '%".$key."%'";
		$surat = mysqli_query($connection,$query_surat);
        if($surat){
            $tempArray = array();
            $resultArray = array();
			while($row = $surat->fetch_object()){
				$tempArray = [
					'id' => $row->id,
					'tanggal' => $row->tanggal,
					'nama' => $row->nama,
					'hasil' => $row->hasil
				];
				array_push($resultArray, $tempArray);
			}
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "get_evaluasi"){
        $id_ukm = $_POST['id_ukm'];
        $query_event = "SELECT * FROM event WHERE id_ukm = '".$id_ukm."'";
        $event = mysqli_query($connection,$query_event);
        if($event){
            $tempArray = array();
            $resultArray = array();
            while($row = $event->fetch_object()){
                $query_ukm = "SELECT nama_ukm FROM ukm WHERE id ='".$row->id_ukm."'";
				$ukm = mysqli_query($connection,$query_ukm);
				$hasil_ukm = mysqli_fetch_array($ukm);
				$tempArray = [
                    'id' => $row->id,
                    'nama_event' => $row->nama_event,
                    'tanggal' => $row->tanggal,
                    'thumbnail' => $row->thumbnail,
                    'deskripsi' => $row->deskripsi,
                    'evaluasi' => $row->evaluasi,
                    'id_ukm' => $row->id_ukm,
                    'nama_ukm' => $hasil_ukm['nama_ukm']
                ];
                array_push($resultArray, $tempArray);
            }
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
	
	if($flag == "search_evaluasi"){
        $id_ukm = $_POST['id_ukm'];      
        $key = $_POST['key'];
        
		$query_surat = "SELECT * FROM event WHERE nama LIKE '%".$key."%'";
		$surat = mysqli_query($connection,$query_surat);
        if($surat){
            $tempArray = array();
            $resultArray = array();
			while($row = $surat->fetch_object()){
				$query_ukm = "SELECT nama_ukm FROM ukm WHERE id ='".$row->id_ukm."'";
				$ukm = mysqli_query($connection,$query_ukm);
				$hasil_ukm = mysqli_fetch_array($ukm);
				$tempArray = [
                    'id' => $row->id,
                    'nama_event' => $row->nama_event,
                    'tanggal' => $row->tanggal,
                    'thumbnail' => $row->thumbnail,
                    'deskripsi' => $row->deskripsi,
                    'evaluasi' => $row->evaluasi,
                    'id_ukm' => $row->id_ukm,
                    'nama_ukm' => $hasil_ukm['nama_ukm']
                ];
                array_push($resultArray, $tempArray);
			}
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "get_inventaris"){
        $id_ukm = $_POST['id_ukm'];
        $tipe = $_POST['tipe'];
		
		$query_inventaris = "SELECT * FROM inventaris WHERE id_ukm = '".$id_ukm."' ORDER BY nama";
		$inventaris = mysqli_query($connection,$query_inventaris);
        
		if($inventaris){
            $resultArray = array();
			while($row = $inventaris->fetch_object()){
				$tempArray = array();
				if($tipe == "2"){
					$tempArray = [
						'id' => $row->id,
						'nama' => $row->nama,
						'jumlah' => $row->jumlah
					];
				}else if($tipe == "1"){
					if($row->jumlah_dipinjam > 0 ){
						$tempArray = [
							'id' => $row->id,
							'nama' => $row->nama,
							'jumlah' => $row->jumlah_dipinjam
						];
					}
				}else if($tipe == "0"){
					$jumlah =(string) ($row->jumlah - $row->jumlah_dipinjam);
					if($jumlah != "0"){
						$tempArray = [
							'id' => $row->id,
							'nama' => $row->nama,
							'jumlah' => $jumlah
						];
					}
				}
				if(!empty($tempArray))
					array_push($resultArray, $tempArray);
			}
		}
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "get_pinjam"){
        $id_ukm = $_POST['id_ukm'];
        $query_inventaris = "SELECT * FROM inventaris WHERE id_ukm = '".$id_ukm."'";
        $inventaris = mysqli_query($connection,$query_inventaris);
        if($inventaris){
            $resultArray = array();
            while($row = $inventaris->fetch_object()){
				$tempArray = array();
                $jumlah_tersedia = $row->jumlah - $row->jumlah_dipinjam;
				
				if(!empty($jumlah_tersedia)){
					$tempArray = [
						'id' => $row->id,
						'nama' => $row->nama,
						'jumlah_tersedia' => $jumlah_tersedia
					];
					array_push($resultArray, $tempArray);
				}
            }
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
	
	if($flag == "save_pinjam"){

        $id_pengurus = $_POST['id_pengurus'];
        $id_inventaris = $_POST['id_inventaris'];
        $jumlah = $_POST['jumlah'];
		
        $query_inventaris = "SELECT * FROM inventaris WHERE id = '".$id_inventaris."'";
        $inventaris = mysqli_query($connection,$query_inventaris);
        if($inventaris){
			$hasil_inventaris = mysqli_fetch_array($inventaris);
            $jumlah_dipinjam = $hasil_inventaris['jumlah_dipinjam'] + $jumlah;
			
			$query_insert = "INSERT INTO inventaris_pinjam(id_pengurus,id_inventaris,jumlah) VALUES ('".$id_pengurus."','".$id_inventaris."','".$jumlah."')";
			$insert = mysqli_query($connection,$query_insert);
			
			$query_update = "UPDATE inventaris SET jumlah_dipinjam = '".$jumlah_dipinjam."' WHERE id = '".$id_inventaris."'";
			$update = mysqli_query($connection,$query_update);
			
			$resultArray['code'] = "1";
			
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
	
	if($flag == "get_saldo"){
        $id_ukm = $_POST['id_ukm'];
		
        $query_masuk = "SELECT SUM(jumlah) as saldo FROM uang WHERE id_ukm = '".$id_ukm."' AND role = 0";
        $masuk = mysqli_query($connection,$query_masuk);
		
		$query_keluar = "SELECT SUM(jumlah) as saldo FROM uang WHERE id_ukm = '".$id_ukm."' AND role = 1";
        $keluar = mysqli_query($connection,$query_keluar);
        if($masuk && $keluar){
            $hasil_masuk = mysqli_fetch_array($masuk);
			$hasil_keluar = mysqli_fetch_array($keluar);
			
			$resultArray['saldo'] = (string) ($hasil_masuk['saldo'] - $hasil_keluar['saldo']);
            
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
	
	if($flag == "save_uang"){
        $id_ukm = $_POST['id_ukm'];
        $jumlah = $_POST['jumlah'];
        $keterangan = $_POST['keterangan'];
        $role = $_POST['role'];
		$date_now = date('Y-m-d H:i:s');
		
        $query_insert = "INSERT INTO uang(id_ukm,tanggal,jumlah,role,keterangan) VALUES ('".$id_ukm."','".$date_now."','".$jumlah."','".$role."','".$keterangan."')";
		$insert = mysqli_query($connection,$query_insert);
		
		if($role == "0"){
			$code = $_POST['code'];
			
			if($code == "0"){
				$nim = $_POST['nim'];
				$nama = $_POST['nama'];	
				$query_get = "SELECT id FROM uang WHERE tanggal = '".$date_now."'";
				$get = mysqli_query($connection,$query_get);
				$hasil_get = mysqli_fetch_array($get);
			
				$query_insert = "INSERT INTO kas(id_ukm,id_uang,nim,nama,tanggal) VALUES ('".$id_ukm."','".$hasil_get['id']."','".$nim."','".$nama."','".$date_now."')";
				$insert = mysqli_query($connection,$query_insert);
			}
		}
		$resultArray['code'] = "1";
            
		echo json_encode($resultArray);
		mysqli_close($connection);
    }
	
	if($flag == "get_history"){
        $id_ukm = $_POST['id_ukm'];
		
        $query_masuk = "SELECT SUM(jumlah) as saldo FROM uang WHERE id_ukm = '".$id_ukm."' AND role = 0";
        $masuk = mysqli_query($connection,$query_masuk);
		
		$query_keluar = "SELECT SUM(jumlah) as saldo FROM uang WHERE id_ukm = '".$id_ukm."' AND role = 1";
        $keluar = mysqli_query($connection,$query_keluar);
        if($masuk && $keluar){
            $hasil_masuk = mysqli_fetch_array($masuk);
			$hasil_keluar = mysqli_fetch_array($keluar);
			
			$resultArray['list'] = array();
			
			$resultArray['saldo'] = (string) ($hasil_masuk['saldo'] - $hasil_keluar['saldo']);
			
			$query_histori = "SELECT * FROM uang WHERE MONTH(tanggal) = MONTH(CURRENT_TIMESTAMP) AND id_ukm = '".$id_ukm."'";
			$histori = mysqli_query($connection,$query_histori);
			
			while($row = $histori->fetch_object()){
				$tempArray = [
					'tanggal' => $row->tanggal,
					'jumlah' => $row->jumlah,
					'keterangan' => $row->keterangan
				];
				array_push($resultArray['list'], $tempArray);
			}
            
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
	
	if($flag == "get_kas"){
        $id_ukm = $_POST['id_ukm'];
        $query_kas = "SELECT nim,nama, COUNT(nim) as jumlah FROM kas WHERE id_ukm = '".$id_ukm."' GROUP BY nim";
        $kas = mysqli_query($connection,$query_kas);
        if($kas){
            $resultArray = array();
            while($row = $kas->fetch_object()){
				$tempArray = [
					'nim' => $row->nim,
					'nama' => $row->nama,
					'jumlah' => $row->jumlah
				];
				array_push($resultArray, $tempArray);
            }
            echo json_encode($resultArray);
            mysqli_close($connection);
        }
    }
?>