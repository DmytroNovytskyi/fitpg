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
    let emailValid = false;
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

//Client-side register form validation
$('#register-form').on('submit', function (event) {
    event.preventDefault();
    const usernameValue = $('#username').val().trim();
    const emailValue = $('#email').val().trim();
    const passwordValue = $('#password').val().trim();
    let usernameValid = false;
    let emailValid = false;
    let passwordValid = false;

    const usernameNotNullMessage = $('#register-username-not-null');
    const usernamePatternMessage = $('#register-username-pattern');
    const emailNotNullMessage = $('#register-email-not-null');
    const emailPatternMessage = $('#register-email-pattern');
    const passwordNotNullMessage = $('#register-password-not-null');
    const passwordPatternMessage = $('#register-password-pattern');
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

//Client-side login form validation
$('#login-form').on('submit', function (event) {
    event.preventDefault();
    const usernameValue = $('#username').val().trim();
    const passwordValue = $('#password').val().trim();
    let usernameValid = false;
    let passwordValid = false;

    const usernameNotNullMessage = $('#login-username-not-null');
    const passwordNotNullMessage = $('#login-password-not-null');
    usernameNotNullMessage.addClass('d-none');
    passwordNotNullMessage.addClass('d-none');

    if (usernameValue === '') {
        usernameNotNullMessage.removeClass('d-none');
    } else {
        usernameValid = true;
    }

    if (passwordValue === '') {
        passwordNotNullMessage.removeClass('d-none');
    } else {
        passwordValid = true;
    }

    if (usernameValid && passwordValid) {
        this.submit();
    }
});

//Exercise select filter by muscle groups
$('#muscleGroups').on('change', function () {
    const selectedMuscleGroups = $.map($('#muscleGroups option:selected'), function (val) {
        return val.innerHTML;
    });
    $('#exerciseInfo option').each(function () {
        const attribute = $(this).attr('muscleGroups');
        if (attribute !== undefined) {
            $(this).hide();
            const muscleGroups = attribute.replace('[', '').replace(']', '').split(',');
            if (muscleGroups.some(m => selectedMuscleGroups.includes(m))) {
                $(this).show();
            }
        }
    });
    const selected = $('#exerciseInfo :selected');
    if (selected.css('display') === 'none') {
        selected.removeAttr('selected')
    }
    const select = $('select#exerciseInfo');
    select.selectpicker('destroy');
    select.selectpicker();
})

//Add exercise set button
$('.add-set-btn').on('click', function () {
    const copy = $('.add-set-template').first()
        .clone()
        .removeClass('add-set-template');
    const prevIndex = $('#sets-form').children().last().find('.index').text();
    const index = prevIndex === '' ? 0 : Number(prevIndex) + 1;
    copy.find('.index').text(index);
    const repetitions = copy.find('.template-repetitions');
    repetitions.attr({
        id: repetitions.attr('id').replace('INDEX', index), name: repetitions.attr('name').replace('INDEX', index)
    })
    repetitions.removeClass('template-repetitions');
    const weight = copy.find('.template-weight');
    weight.attr({
        id: weight.attr('id').replace('INDEX', index), name: weight.attr('name').replace('INDEX', index)
    })
    weight.removeClass('template-weight');
    const selectUnit = copy.find('select.template-unit');
    selectUnit.attr({
        id: selectUnit.attr('id').replace('INDEX', index), name: selectUnit.attr('name').replace('INDEX', index)
    })
    selectUnit.removeClass('template-unit');
    const select = copy.find('select');
    select.parent().find('button').remove();
    select.selectpicker();
    copy.appendTo('#sets-form').show();
})

//Remove exercise set button
$(document).on('click', '.remove-set-btn', function () {
    let next = $(this).parent().parent().next();
    while (next.length !== 0) {
        const indexElem = next.find('.index');
        const nexIndex = Number(indexElem.text()) - 1;
        indexElem.text(nexIndex);
        const id = next.find('.id-input');
        if (id.length !== 0) {
            id.attr({
                id: id.attr('id').replace(/[0-9]+/, nexIndex), name: id.attr('name').replace(/[0-9]+/, nexIndex)
            })
        }
        const repetitions = next.find('.repetitions-input');
        repetitions.attr({
            id: repetitions.attr('id').replace(/[0-9]+/, nexIndex),
            name: repetitions.attr('name').replace(/[0-9]+/, nexIndex)
        })
        const weight = next.find('.weight-input');
        weight.attr({
            id: weight.attr('id').replace(/[0-9]+/, nexIndex), name: weight.attr('name').replace(/[0-9]+/, nexIndex)
        })
        const select = next.find('select');
        select.attr({
            id: select.attr('id').replace(/[0-9]+/, nexIndex), name: select.attr('name').replace(/[0-9]+/, nexIndex)
        })
        select.parent().find('button').remove();
        select.selectpicker('destroy');
        select.selectpicker();
        next = next.next();
    }
    $(this).closest('.form-group').remove();
})

//Client-side exercise edit form validation
$('#exercises-update-form').on('submit', function (event) {
    event.preventDefault();
    const exerciseInfoIdValue = $('#exerciseInfo :selected').attr('value');

    let exerciseInfoId = true;
    let exerciseSetRepetitions = true;
    let exerciseSetWeights = true;
    let exerciseSetUnits = true;

    const exerciseInfoIdNotNullMessage = $('#exercises-update-exercise-info-id-not-null');
    const exerciseSetRepetitionsNotNullMessages = $('.exercises-update-exercise-set-repetitions-not-null');
    const exerciseSetWeightNotNullMessages = $('.exercises-update-exercise-set-weight-not-null');
    const exerciseSetUnitNotNullMessages = $('.exercises-update-exercise-set-unit-not-null');
    exerciseInfoIdNotNullMessage.addClass('d-none');
    exerciseSetRepetitionsNotNullMessages.each(function () {
        $(this).addClass('d-none');
    });
    exerciseSetWeightNotNullMessages.each(function () {
        $(this).addClass('d-none');
    });
    exerciseSetUnitNotNullMessages.each(function () {
        $(this).addClass('d-none');
    });

    if (exerciseInfoIdValue === '') {
        exerciseInfoIdNotNullMessage.removeClass('d-none');
        exerciseInfoId = false;
    }
    $('#sets-form .repetitions-input').each(function (i) {
        if ($(this).val() === '') {
            $(exerciseSetRepetitionsNotNullMessages[i]).removeClass('d-none');
            exerciseSetRepetitions = false;
        }
    })
    $('#sets-form .weight-input').each(function (i) {
        if ($(this).val() === '') {
            $(exerciseSetWeightNotNullMessages[i]).removeClass('d-none');
            exerciseSetWeights = false;
        }
    })
    $('#sets-form select :selected').each(function (i) {
        if ($(this).val() === '') {
            $(exerciseSetUnitNotNullMessages[i]).removeClass('d-none');
            exerciseSetUnits = false;
        }
    })

    if (exerciseInfoId && exerciseSetRepetitions && exerciseSetWeights && exerciseSetUnits) {
        this.submit();
    }
})

//Client-side exercise create form validation
$('#exercises-create-form').on('submit', function (event) {
    event.preventDefault();
    const exerciseInfoIdValue = $('#exerciseInfo :selected').attr('value');

    let exerciseInfoId = true;
    let exerciseSetRepetitions = true;
    let exerciseSetWeights = true;
    let exerciseSetUnits = true;

    const exerciseInfoIdNotNullMessage = $('#exercises-create-exercise-info-id-not-null');
    const exerciseSetRepetitionsNotNullMessages = $('.exercises-create-exercise-set-repetitions-not-null');
    const exerciseSetWeightNotNullMessages = $('.exercises-create-exercise-set-weight-not-null');
    const exerciseSetUnitNotNullMessages = $('.exercises-create-exercise-set-unit-not-null');
    exerciseInfoIdNotNullMessage.addClass('d-none');
    exerciseSetRepetitionsNotNullMessages.each(function () {
        $(this).addClass('d-none');
    });
    exerciseSetWeightNotNullMessages.each(function () {
        $(this).addClass('d-none');
    });
    exerciseSetUnitNotNullMessages.each(function () {
        $(this).addClass('d-none');
    });

    if (exerciseInfoIdValue === '') {
        exerciseInfoIdNotNullMessage.removeClass('d-none');
        exerciseInfoId = false;
    }
    $('#sets-form .repetitions-input').each(function (i) {
        if ($(this).val() === '') {
            $(exerciseSetRepetitionsNotNullMessages[i]).removeClass('d-none');
            exerciseSetRepetitions = false;
        }
    })
    $('#sets-form .weight-input').each(function (i) {
        if ($(this).val() === '') {
            $(exerciseSetWeightNotNullMessages[i]).removeClass('d-none');
            exerciseSetWeights = false;
        }
    })
    $('#sets-form select :selected').each(function (i) {
        if ($(this).val() === '') {
            $(exerciseSetUnitNotNullMessages[i]).removeClass('d-none');
            exerciseSetUnits = false;
        }
    })

    if (exerciseInfoId && exerciseSetRepetitions && exerciseSetWeights && exerciseSetUnits) {
        this.submit();
    }
})

//Workouts create datepicker
$(function () {
    $('#datepicker').datepicker({
        format: "dd-mm-yyyy"
    });
});