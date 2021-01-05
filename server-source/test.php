 <?php  
     $file_path = "./picture/";
     $var = $_POST['result'];
     $file_path1 = $file_path . basename( $_FILES['uploaded_file1']['name']);
     if(move_uploaded_file($_FILES['uploaded_file1']['tmp_name'], $file_path1)) {
         $result = array("result" => "success", "value" => $var);
     } else{
         $result = array("result" => "error");
     }
     $file_path2 = $file_path . basename( $_FILES['uploaded_file2']['name']);
     if(move_uploaded_file($_FILES['uploaded_file2']['tmp_name'], $file_path2)) {
         $result = array("result" => "success", "value" => $var);
     } else{
         $result = array("result" => "error");
     }
     $file_path3 = $file_path . basename( $_FILES['uploaded_file3']['name']);
     if(move_uploaded_file($_FILES['uploaded_file3']['tmp_name'], $file_path3)) {
         $result = array("result" => "success", "value" => $var);
     } else{
         $result = array("result" => "error");
     }
     $file_path4 = $file_path . basename( $_FILES['uploaded_file4']['name']);
     if(move_uploaded_file($_FILES['uploaded_file4']['tmp_name'], $file_path4)) {
         $result = array("result" => "success", "value" => $var);
     } else{
         $result = array("result" => "error");
     }
     $file_path5 = $file_path . basename( $_FILES['uploaded_file5']['name']);
     if(move_uploaded_file($_FILES['uploaded_file5']['tmp_name'], $file_path5)) {
         $result = array("result" => "success", "value" => $var);
     } else
         $result = array("result" => "error");
     }
 ?>
