$(document).ready(function () {
    // console.clear();

    const contactItems = document.querySelectorAll('.c-contact');

    const updateCount = () => {
        const contactItems = document.querySelectorAll('.c-contact');
        let count = contactItems.length;
        if (count > 1) {
            document.getElementById('teamCount').innerHTML = `(${count} thành viên)`;
        } else if (count == 1) {
            document.getElementById('teamCount').innerHTML = `(${count} thành viên)`;
        } else {
            document.getElementById('teamCount').innerHTML = `(No team members)`;
        }
    };

    updateCount();

    contactItems.forEach(contact => {
        // assign bg to avatars
        const avatar = contact.querySelector('.c-contact__avatar');
        if (avatar) {
            const avatarUrl = avatar.getAttribute('data-avatar');
            avatar.style.backgroundImage = `url('${avatarUrl}')`;
        }

        // click events for buttons
        const viewBtn = contact.querySelector('.c-button--view');
        const deleteBtn = contact.querySelector('.c-button--delete');
        if (viewBtn && deleteBtn) {

            deleteBtn.addEventListener('click', deleteItem);

            viewBtn.addEventListener('click', () => {
                console.log('Mở modal xem chi tiết thành viên thuộc báo cáo!');
            });

        }

    });

    const emptyState = x => {
        const newEmptyState = document.createElement('div');
        newEmptyState.classList = 'c-empty-state u-text--center';
        newEmptyState.innerHTML = `
		<img class="c-empty-state__hero" src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/813538/emptyState.svg" />
		<h3 class="u-text--regular">No team members</h3>
		<p>There are currently no members on this team. Any new user you add to the team will appear here.</p><br/>
	`;
        x.appendChild(newEmptyState);
    };

    function deleteItem() {
        // const transitionTime = 250;
        // const contact = this.parentNode.parentNode;
        // contact.style.transition = `all ${transitionTime}ms ease-out 0s`;
        // contact.style.transform = 'translateX(4rem)';
        // contact.style.opacity = 0;
        // setTimeout(() => {
        //     if (contact.parentNode.querySelectorAll('.c-contact').length < 2) {
        //         emptyState(contact.parentNode);
        //     } else {
        //         if (contact.parentNode.querySelector('.c-empty-state')) {
        //             document.querySelector('.c-empty-state').remove();
        //         }
        //     }
        //     contact.remove();
        //     updateCount();
        // }, transitionTime);

        const transitionTime = 250;
        const contact = this.parentNode.parentNode;

        let thisDataId = this.getAttribute('data-id');

        if (thisDataId) {
            let thisDataFullname = this.getAttribute('data-fullname');
            alertify.confirm(
                'Xác nhận xóa',
                '<p class="text-center pb-2"><i class="feather icon-alert-circle text-warning h1"></i></p>'
                + '<p class="text-center">'
                + 'Thành viên <span class="text-primary font-weight-bold">' + thisDataFullname
                + '</span> sẽ không thuộc báo cáo hiện tại nữa.<br>'
                + 'Bạn chắc chứ?'
                + '</p>',
                function () {
                    // Ok => Call ajax deleteMember
                    $.ajax({
                        url: "/api/deleteMember/" + thisDataId,
                        success: function (result) {
                            if (result.status == "success") {
                                contact.style.transition = `all ${transitionTime}ms ease-out 0s`;
                                contact.style.transform = 'translateX(4rem)';
                                contact.style.opacity = 0;
                                setTimeout(() => {
                                    if (contact.parentNode.querySelectorAll('.c-contact').length < 2) {
                                        emptyState(contact.parentNode);
                                    } else {
                                        if (contact.parentNode.querySelector('.c-empty-state')) {
                                            document.querySelector('.c-empty-state').remove();
                                        }
                                    }
                                    contact.remove();
                                    updateCount();
                                }, transitionTime);

                                alertify.success(result.message).delay(1.5);
                                return;
                            }
                            if (result.status == "failed") {
                                alertify.error(result.message).delay(1.5);
                                return;
                            }
                        },
                        error: function () {
                            alertify.error("Thất bại! Vui lòng thử lại sau.").delay(1.5);
                        }
                    });
                },
                function () {
                    // Cancel => Do nothing
                }
            );
        } else {
            contact.style.transition = `all ${transitionTime}ms ease-out 0s`;
            contact.style.transform = 'translateX(4rem)';
            contact.style.opacity = 0;
            setTimeout(() => {
                if (contact.parentNode.querySelectorAll('.c-contact').length < 2) {
                    emptyState(contact.parentNode);
                } else {
                    if (contact.parentNode.querySelector('.c-empty-state')) {
                        document.querySelector('.c-empty-state').remove();
                    }
                }
                contact.remove();
                updateCount();
            }, transitionTime);
        }
    }

    const states = ['info', 'primary', 'danger', 'success', 'warning'];

    const createAlert = (target, state, content) => {
        const newAlert = document.createElement('div');
        newAlert.classList = `c-alert c-alert--${state}`;
        newAlert.innerHTML = content;
        target.prepend(newAlert);
    };

    const closeMobileForm = () => {
        document.querySelector('.u-hide--mobile').classList.add('is-closing');
        setTimeout(() => {
            document.querySelector('.u-hide--mobile').classList.remove('is-open');
            document.querySelector('.u-hide--mobile').classList.remove('is-closing');
        }, 240);
    };

    const sendForm = () => {
        const nameInput = document.getElementById('newInviteName');
        const positionInput = document.getElementById('newInvitePosition');
        const jobAssignedInput = tinymce.get("newJobAssigned").getContent();
        const randomState = Math.floor(Math.random() * 4);

        if (!nameInput.value.length == 0 && !positionInput.value.length == 0 && !jobAssignedInput.length == 0) {
            // if (addMember(jobAssignedInput) == true) {
            //     console.log("Thêm thành viên mới thành công!");
            // }

            const list = document.querySelector('.c-list');
            const newItem = document.createElement('li');
            newItem.classList = 'c-contact';
            newItem.innerHTML = `
			<div class="c-contact__left">
				<div class="c-contact__avatar" style="background-color: var(--${states[randomState]})">${nameInput.value.charAt(0)}</div>
				<div class="c-contact__content">
					<div class="c-contact__name">${nameInput.value}</div>
					<small class="c-contact__description">${positionInput.value}</small>
				</div>
			</div>
			<div class="l-actions c-contact__right">
				<div class="c-button c-button--danger c-button--sm c-button--delete">Delete</div>
				<div class="c-button c-button--default c-button--sm c-button--view">View</div>
			</div>
		`;
            if (list.querySelector('.c-empty-state')) {
                list.querySelector('.c-empty-state').remove();
            }
            if (document.getElementById('addForm').querySelector('.c-alert')) {
                document.getElementById('addForm').querySelector('.c-alert').remove();
            }
            createAlert(document.getElementById('addForm'), 'success', `${nameInput.value} added to team!`);
            newItem.querySelector('.c-button--delete').addEventListener('click', deleteItem);
            list.prepend(newItem);
            nameInput.value = '';
            positionInput.value = '';
            updateCount();
            if (document.querySelector('.u-hide--mobile.is-open') && document.querySelector('.c-overlay')) {
                closeMobileForm();
                document.querySelector('.c-overlay').remove();
            }
        } else {
            if (document.getElementById('addForm').querySelector('.c-alert')) {
                document.getElementById('addForm').querySelector('.c-alert').remove();
            }
            createAlert(document.getElementById('addForm'), 'danger', `Bạn chưa cung cấp đủ thông tin.`);
        }
    };

    const newMemberBtn = document.getElementById('addMember');

    newMemberBtn.addEventListener('click', sendForm);

    const mobileAddBtn = document.getElementById('mobileAddBtn');

    const createOverlay = () => {
        const newOverlay = document.createElement('div');
        newOverlay.classList = 'c-overlay';
        newOverlay.addEventListener('click', () => {
            newOverlay.remove();
            if (document.querySelector('.u-hide--mobile')) {
                closeMobileForm();
            }
        });
        document.body.appendChild(newOverlay);
    };

    document.getElementById('closeMobileAdd').addEventListener('click', () => {
        if (document.querySelector('.u-hide--mobile.is-open') && document.querySelector('.c-overlay')) {
            closeMobileForm();
            document.querySelector('.c-overlay').remove();
        }
    });

    const openMobileAdd = () => {
        const mobileHide = document.querySelector('.u-hide--mobile');
        mobileHide.classList.add('is-open');
        createOverlay();
    };

    mobileAddBtn.addEventListener('click', openMobileAdd);
});