import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent {
  @Output() onSumbitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();
  login: string = '';
  password: string = '';
  active: string = 'login';
  firstName: string = '';
  lastName: string = '';

  onSubmitLogin(): void {
    this.onSumbitLoginEvent.emit({
      login: this.login,
      password: this.password,
    });
  }

  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit({
      firstName: this.firstName,
      lastName: this.lastName,
      login: this.login,
      password: this.password,
    });
  }

  onRegisterTab() {
    this.active = 'register';
  }

  onLoginTab(): void {
    this.active = 'login';
  }
}
