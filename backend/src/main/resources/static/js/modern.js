
$(document).ready(function () {
    $('#patientRegForm').submit(function (e) {
        e.preventDefault();
        var data = {};
        $(this).serializeArray().forEach(item => data[item.name] = item.value);
        if (data.password !== data.cpassword) { alert("Passwords do not match!"); return; }
        $.ajax({
            url: '/api/public/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function () { alert("Registration successful!"); location.href = "index1.html"; },
            error: function (err) { alert("Error: " + err.responseText); }
        });
    });

    if ($('#bookForm').length > 0) {
        $.get('/api/admin/doctors', function (docs) {
            $('#spec').change(function () {
                const spec = $(this).val();
                const filtered = docs.filter(d => d.spec === spec);
                $('#doctorSelect').empty().prop('disabled', false).append('<option disabled selected>Pick Doctor</option>');
                filtered.forEach(d => $('#doctorSelect').append(`<option value="${d.username}" data-fees="${d.docFees}">${d.username}</option>`));
            });
        });

        $('#doctorSelect').change(function () { $('#docFees').val($(this).find(':selected').data('fees')); updateTimeSlots(); });
        $('input[name="appdate"]').change(updateTimeSlots);

        const todayStr = new Date().toISOString().split('T')[0];
        $('input[name="appdate"]').attr('min', todayStr);

        $('#bookForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => data[i.name] = i.value);
            data.docFees = $('#docFees').val();
            $.ajax({
                url: '/api/patient/book', type: 'POST', contentType: 'application/json', data: JSON.stringify(data),
                success: function () { alert("Booking Confirmed! Check Email."); location.reload(); },
                error: function (xhr) { alert(xhr.responseText || "Error booking appointment"); }
            });
        });
    }

    if ($('#histBody').length > 0) {
        $.get('/api/patient/appointments', function (data) {
            data.forEach(a => {
                let st = "Active", cls = "badge-pulse";
                if (a.userStatus == 0 || a.doctorStatus == 0) { st = "Cancelled"; cls = "badge-danger"; }
                else if (a.doctorStatus == 2) { st = "Completed"; cls = "badge-success"; }
                $('#histBody').append(`<tr><td>Dr. ${a.doctor}</td><td>$${a.docFees}</td><td>${a.appdate}</td><td>${a.apptime}</td>
                    <td><span class="badge ${cls}">${st}</span></td>
                    <td>${st === "Active" ? `<button onclick="cancelApp(${a.id})" class="btn btn-outline-danger btn-sm">Cancel</button>` : ""}</td></tr>`);
            });
        });
    }

    if ($('#presBody').length > 0) {
        $.get('/api/patient/prescriptions', function (data) {
            window.allPres = data;
            data.forEach((p, idx) => $('#presBody').append(`<tr><td>DR. ${p.doctor.toUpperCase()}</td><td>${p.appdate}</td><td>${p.disease}</td>
                <td><button onclick="viewPad(${idx})" class="btn btn-pulse btn-sm px-3">View Pad</button></td></tr>`));
        });
    }

    if ($('#profileForm').length > 0) {
        $.get('/api/patient/profile', function (p) {
            $('#profEmail').val(p.email);
            $('#profileForm [name="fname"]').val(p.fname);
            $('#profileForm [name="lname"]').val(p.lname);
            $('#profileForm [name="contact"]').val(p.contact);
        });

        $('#profileForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => { if (i.value) data[i.name] = i.value; });
            $.ajax({ url: '/api/patient/profile', type: 'PUT', contentType: 'application/json', data: JSON.stringify(data), success: function () { alert("Profile Updated Successfully"); } });
        });
    }

    if ($('#appBody').length > 0) {
        $.get('/api/doctor/appointments', function (data) {
            data.forEach(a => {
                let st = "Active", cls = "badge-pulse";
                if (a.userStatus == 0 || a.doctorStatus == 0) { st = "Cancelled"; cls = "badge-danger"; }
                else if (a.doctorStatus == 2) { st = "Prescribed"; cls = "badge-success"; }

                $('#appBody').append(`<tr class="app-row" data-contact="${a.contact}">
                    <td>${a.fname} ${a.lname} <br><small class="text-muted">#PT${a.pid} • ${a.gender}</small></td>
                    <td>${a.gender}</td><td>${a.appdate}</td><td>${a.apptime}</td>
                    <td><span class="badge ${cls}">${st}</span></td>
                    <td>${st === "Active" ? `<button onclick='openPresModal(${a.pid}, ${a.id}, \`${(a.disease || '').replace(/`/g, "\\`")}\`)' class="btn btn-outline-success btn-sm" style="position: relative; z-index: 10;">Prescribe</button> <button onclick="cancelApp(${a.id})" class="btn btn-outline-danger btn-sm" style="position: relative; z-index: 10;">Cancel</button>` : "-"}</td></tr>`);
            });
        });

        $('#appSearch').on('keyup', function () {
            var value = $(this).val();
            $(".app-row").filter(function () { $(this).toggle($(this).data('contact').toString().indexOf(value) > -1); });
        });

        loadAvailability();

        $.get('/api/doctor/profile', function (d) {
            $('#profEmail').val(d.email);
            $('#profileForm [name="username"]').val(d.username);
            $('#profileForm [name="spec"]').val(d.spec);
            $('#profileForm [name="docFees"]').val(d.docFees);
        });

        $('#profileForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => { if (i.value) data[i.name] = i.value; });
            $.ajax({ url: '/api/doctor/profile', type: 'PUT', contentType: 'application/json', data: JSON.stringify(data), success: function () { alert("Profile Updated!"); } });
        });

        $('#availForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => data[i.name] = i.value);
            $.ajax({ url: '/api/doctor/availability', type: 'POST', contentType: 'application/json', data: JSON.stringify(data), success: function () { alert("Slot added!"); loadAvailability(); } });
        });

        $('#presForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => data[i.name] = i.value);
            $.ajax({ url: '/api/doctor/prescribe', type: 'POST', contentType: 'application/json', data: JSON.stringify(data), success: function () { alert("Prescribed!"); location.reload(); } });
        });

        $('#presPreset').on('change', function() {
            const protocols = {
                'flu': { 
                    symptoms: 'Fever, cough, body ache, fatigue, mild congestion.', 
                    prescription: 'Paracetamol 500mg: 1 tab 3x daily (after meals).\nCough Syrup: 10ml twice daily.\nVitamin C: 1000mg once daily.\nAdvised: Complete bed rest for 3 days and high fluid intake.' 
                },
                'migraine': { 
                    symptoms: 'Severe unilateral headache, nausea, sensitivity to light/sound.', 
                    prescription: 'Sumatriptan 50mg: Take 1 tab at onset of pain.\nNaproxen 500mg: Twice daily as needed for pain.\nAdvised: Rest in a dark, quiet room. Maintain regular sleep patterns.' 
                },
                'hypertension': { 
                    symptoms: 'Asymptomatic (Routine check), Blood Pressure elevated (145/95).', 
                    prescription: 'Amlodipine 5mg: 1 tab once daily (morning).\nAdvised: Low sodium diet, 30 min daily brisk walking, stress management. Review BP in 1 week.' 
                },
                'wellness': { 
                    symptoms: 'General weakness, periodic fatigue, checkup request.', 
                    prescription: 'Multivitamin (A-Z): 1 tab daily after breakfast.\nVitamin D3 2000IU: Once daily for 2 months.\nAdvised: Increase protein intake, maintain 7-8 hours of sleep.' 
                }
            };
            const selected = protocols[$(this).val()];
            if(selected) {
                $('#disease').val(selected.symptoms);
                $('#prescription').val(selected.prescription);
            }
        });
    }

    if ($('#docBody').length > 0) {
        loadDocs(); loadPats(); loadApps(); loadMes(); loadPres();
        
        $('#addDocForm').submit(function(e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => data[i.name] = i.value);
        });
    }

    if ($('#contactForm').length > 0) {
        $('#contactForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            $(this).serializeArray().forEach(i => data[i.name] = i.value);
            $.ajax({
                url: '/api/public/contact',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function () {
                    alert('Message successfully transmitted. Our team will contact you shortly.');
                    $('#contactForm')[0].reset();
                },
                error: function () { alert('Error connecting to transmission relay. Please try again.'); }
            });
        });
    }
});


function updateTimeSlots() {
    const doctor = $('#doctorSelect').val();
    const date = $('input[name="appdate"]').val();
    if (!doctor || !date) return;

    const now = new Date();
    const todayStr = now.toLocaleDateString('en-CA');
    const currentTime = now.getHours().toString().padStart(2, '0') + ":" + now.getMinutes().toString().padStart(2, '0') + ":" + now.getSeconds().toString().padStart(2, '0');

    $.get(`/api/patient/doctor-availability/${doctor}/${date}`, function (results) {
        $('#timeSelect').empty().prop('disabled', false);
        const d = new Date(date + "T00:00:00");
        const day = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];

        const filtered = results.filter(a => {
            if (a.dayOfWeek !== day) return false;
            if (date === todayStr) { return a.startTime > currentTime; }
            return true;
        });

        if (filtered.length === 0) {
            $('#timeSelect').append(`<option disabled selected>No slots available for ${date}</option>`).prop('disabled', true);
        } else {
            $('#timeSelect').append('<option disabled selected>Pick Slot</option>');
            filtered.forEach(a => $('#timeSelect').append(`<option value="${a.startTime}">${a.startTime} - ${a.endTime}</option>`));
        }
    });
}

function cancelApp(id) { if (confirm("Are you sure?")) { $.post('/api/patient/cancel/' + id, function () { location.reload(); }); } }

function viewPad(idx) {
    const p = window.allPres[idx];
    $('#padPatName').text(`${p.fname} ${p.lname}`);
    $('#padPatID').text(`#PT${p.pid}`);
    $('#padDocName').text(`Dr. ${p.doctor}`);
    $('#padDate').text(`${p.appdate} | ${p.apptime}`);
    $('#padRef').text(`REF: RX-${p.appointmentId}`);
    $('#padSymptoms').text(p.disease);
    $('#padRx').text(p.prescription);
    $('#padModal').modal('show');
}

function printPad() {
    var printContents = document.getElementById('printArea').innerHTML;
    var originalContents = document.body.innerHTML;
    document.body.innerHTML = "<html><head><title>Prescription</title><link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'></head><body>" + printContents + "</body></html>";
    window.print();
    document.body.innerHTML = originalContents;
    location.reload();
}

function loadDocs() {
    $.get('/api/admin/doctors', function(data) {
        $('#docBody').empty();
        data.forEach(d => $('#docBody').append(`<tr><td>Dr. ${d.username}</td><td>${d.spec}</td><td>${d.email}</td><td>$${d.docFees}</td>
            <td><button onclick="removeDoc('${d.email}')" class="btn btn-outline-danger btn-sm border-0"><i class="fa fa-trash"></i></button></td></tr>`));
    });
}
function loadPats() {
    $.get('/api/admin/patients', function(data) {
        $('#patBody').empty();
        data.forEach(p => $('#patBody').append(`<tr><td>#PT${p.pid}</td><td>${p.fname} ${p.lname}</td><td>${p.gender}</td><td>${p.email}</td><td>${p.contact}</td>
            <td><button onclick="removePat(${p.pid})" class="btn btn-outline-danger btn-sm border-0"><i class="fa fa-trash"></i></button></td></tr>`));
    });
}
function loadApps() {
    $.get('/api/admin/appointments', function(data) {
        $('#appDetailsBody').empty();
        data.forEach(a => {
            let status = "Active", cls = "badge-pulse";
            if (a.userStatus == 0 || a.doctorStatus == 0) { status = "Cancelled"; cls = "badge-danger"; }
            else if (a.doctorStatus == 2) { status = "Prescribed"; cls = "badge-success"; }
            $('#appDetailsBody').append(`<tr><td>#APP${a.id}</td><td>${a.fname} ${a.lname} <br><small class="text-muted">${a.contact}</small></td><td>Dr. ${a.doctor}</td><td>$${a.docFees}</td><td>${a.appdate}</td>
                <td><span class="badge ${cls}">${status}</span></td><td><button onclick="removeApp(${a.id})" class="btn btn-outline-danger btn-sm border-0"><i class="fa fa-trash"></i></button></td></tr>`);
        });
    });
}
function loadPres() {
    $.get('/api/admin/prescriptions', function(data) {
        window.allPres = data;
        $('#presDetailsBody').empty();
        data.forEach((p, idx) => $('#presDetailsBody').append(`<tr><td>#APP${p.appointmentId}</td><td>${p.fname} ${p.lname}</td><td>Dr. ${p.doctor}</td><td>${p.appdate}</td><td>${p.disease}</td><td>${p.prescription}</td>
            <td><button onclick="viewPad(${idx})" class="btn btn-pulse btn-sm px-3">View Pad</button></td></tr>`));
    });
}
function loadMes() {
    $.get('/api/admin/messages', function(data) {
        $('#mesBody').empty();
        data.forEach(m => { $('#mesBody').append(`<tr><td>${m.name}</td><td>${m.email}</td><td>${m.contact}</td><td>${m.message}</td></tr>`); });
    });
}
function removeDoc(email) { if(confirm("Remove Doctor Registry?")) { $.ajax({ url: '/api/admin/remove-doctor/' + email, type: 'DELETE', success: function() { loadDocs(); } }); } }
function removePat(id) { if(confirm("Deactivate Patient Account?")) { $.ajax({ url: '/api/admin/remove-patient/' + id, type: 'DELETE', success: function() { loadPats(); } }); } }
function removeApp(id) { if(confirm("Purge Appointment Record?")) { $.ajax({ url: '/api/admin/remove-appointment/' + id, type: 'DELETE', success: function() { loadApps(); } }); } }

function loadAvailability() {
    $.get('/api/doctor/availability', function (data) {
        $('#availBody').empty();
        data.forEach(a => $('#availBody').append(`<tr><td>${a.dayOfWeek}</td><td>${a.startTime}</td><td>${a.endTime}</td><td><button onclick="removeAvail(${a.id})" class="btn btn-outline-danger btn-sm border-0"><i class="fa fa-trash"></i></button></td></tr>`));
    });
}
function removeAvail(id) { if (confirm("Remove slot?")) { $.ajax({ url: '/api/doctor/availability/' + id, type: 'DELETE', success: function () { loadAvailability(); } }); } }

function initRecovery() {
    if ($('#forgotForm').length > 0) {
        $('#forgotForm').submit(function(e) {
            e.preventDefault();
            const email = $('input[name="email"]').val();
            $('#message').show().html('<i class="fa fa-spinner fa-spin mr-2"></i> Transmitting...');
            $.ajax({
                url: '/api/auth/forgot-password', type: 'POST', contentType: 'application/json',
                data: JSON.stringify({ email: email }),
                success: function(res) {
                    $('#message').html('<span class="text-accent"><i class="fa fa-check-circle mr-2"></i> ' + res + '</span>');
                    $('#forgotForm').fadeOut();
                },
                error: function(xhr) { $('#message').html('<span class="text-danger"><i class="fa fa-exclamation-triangle mr-2"></i> ' + xhr.responseText + '</span>'); }
            });
        });
    }

    if ($('#resetForm').length > 0 || $('#errorContainer').length > 0) {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        if (!token) {
            $('#errorContainer').show();
        } else {
            $.get('/api/auth/validate-token?token=' + token, function() {
                $('#tokenField').val(token);
                $('#resetContainer').show();
            }).fail(function() {
                $('#errorContainer').show();
            });
        }

        $('#resetForm').submit(function(e) {
            e.preventDefault();
            const password = $('input[name="password"]').val();
            const cpassword = $('#cpass').val(); // using ID from HTML
            if (password !== cpassword) { alert("Passwords do not match!"); return; }
            $.ajax({
                url: '/api/auth/reset-password', type: 'POST', contentType: 'application/json',
                data: JSON.stringify({ token: token, password: password }),
                success: function() { alert("Security synchronized successfully. Redirecting..."); window.location.href = "index1.html"; },
                error: function(xhr) { alert(xhr.responseText); }
            });
        });
    }
}

$(document).ready(function() {
    initRecovery();
});
