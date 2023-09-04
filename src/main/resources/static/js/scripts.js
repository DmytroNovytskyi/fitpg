//Toggle password visibility
$('#show-hide-password a').on('click', function (event) {
    event.preventDefault();
    const showHidePasswordInput = $('#show-hide-password input');
    const showHidePasswordI = $('#show-hide-password i');
    if (showHidePasswordInput.attr('type') === 'text') {
        showHidePasswordInput.attr('type', 'password');
        showHidePasswordI.addClass('fa-eye-slash').removeClass('fa-eye');
    } else if (showHidePasswordInput.attr('type') === 'password') {
        showHidePasswordInput.attr('type', 'text');
        showHidePasswordI.removeClass('fa-eye-slash').addClass('fa-eye');
    }
});

//Regex
const usernameRegex = /^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,8}[a-zA-Z0-9]$/;
const emailRegex = /^(?=[a-zA-Z0-9._@%-]{6,255}$)[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,64}$/;
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,32}$/;

//Client-side users create form validation
$('#users-create-form').on('submit', function (event) {
    event.preventDefault();
    const usernameValue = $('#username').val().trim();
    const emailValue = $('#email').val().trim();
    const passwordValue = $('#password').val().trim();
    let usernameValid = false;
    let emailValid = false;
    let passwordValid = false;

    const usernameNotNullMessage = $('#users-create-username-not-null');
    const usernamePatternMessage = $('#users-create-username-pattern');
    const emailNotNullMessage = $('#users-create-email-not-null');
    const emailPatternMessage = $('#users-create-email-pattern');
    const passwordNotNullMessage = $('#users-create-password-not-null');
    const passwordPatternMessage = $('#users-create-password-pattern');
    usernameNotNullMessage.addClass('d-none');
    usernamePatternMessage.addClass('d-none');
    emailNotNullMessage.addClass('d-none');
    emailPatternMessage.addClass('d-none');
    passwordNotNullMessage.addClass('d-none');
    passwordPatternMessage.addClass('d-none');

    if (usernameValue === '') {
        usernameNotNullMessage.removeClass('d-none');
    } else if (!usernameRegex.test(usernameValue)) {
        usernamePatternMessage.removeClass('d-none');
    } else {
        usernameValid = true;
    }

    if (emailValue === '') {
        emailNotNullMessage.removeClass('d-none');
    } else if (!emailRegex.test(emailValue)) {
        emailPatternMessage.removeClass('d-none');
    } else {
        emailValid = true;
    }

    if (passwordValue === '') {
        passwordNotNullMessage.removeClass('d-none');
    } else if (!passwordRegex.test(passwordValue)) {
        passwordPatternMessage.removeClass('d-none');
    } else {
        passwordValid = true;
    }

    if (usernameValid && emailValid && passwordValid) {
        this.submit();
    }
});

//Client-side users update form validation
$('#users-update-form').on('submit', function (event) {
    event.preventDefault();
    const emailValue = $('#email').val().trim();
    const passwordValue = $('#password').val().trim();
    let emailValid = true;
    let passwordValid = false;

    const emailNotNullMessage = $('#users-update-email-not-null');
    const emailPatternMessage = $('#users-update-email-pattern');
    const passwordPatternMessage = $('#users-update-password-pattern');
    emailNotNullMessage.addClass('d-none');
    emailPatternMessage.addClass('d-none');
    passwordPatternMessage.addClass('d-none');

    if (emailValue === '') {
        emailNotNullMessage.removeClass('d-none');
    } else if (!emailRegex.test(emailValue)) {
        emailPatternMessage.removeClass('d-none');
    } else {
        emailValid = true;
    }

    if (passwordValue !== '' && !passwordRegex.test(passwordValue)) {
        passwordPatternMessage.removeClass('d-none');
    } else {
        passwordValid = true;
    }

    if (emailValid && passwordValid) {
        this.submit();
    }
});