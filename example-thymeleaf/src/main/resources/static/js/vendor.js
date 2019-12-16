$(document).ready(function(){
    $("#divNpwFile").hide();
    $("#divCoverBankAcc").hide();
    $("#divNpwpNo").hide();
    $("#divCoverBankAcc").hide();


    $("#haveNpwp").change(function(){
        if($(this).val() === 'true'){
            $("#divNpwFile").show();
            $("#divNpwpNo").show();
            $("npwpNo").val("please enter npwp number")
        }
        if($(this).val() === 'false'){
            $("#divNpwFile").hide();
            $("#divNpwpNo").hide();
            $("#npwpNo").val("0000000000")
        }
        if($(this).val() == ''){
             $("#divNpwFile").hide();
             $("#divNpwpNo").hide();
        }
    });

    $("#haveCoverBank").change(function(){
        if($(this).val() === 'true'){
            $("#divCoverBankAcc").show();
        }
        if($(this).val() === 'false'){
            $("#divCoverBankAcc").hide();
        }
        if($(this).val() === ''){
            $("#divCoverBankAcc").hide();
        }
    });

});
