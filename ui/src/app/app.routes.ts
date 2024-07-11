import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProjectsComponent } from './projects/projects.component';
import { UsersComponent } from './users/users.component';
import { UpdateUserComponent } from './users/update-user/update-user.component';
import { CreateProjectComponent } from './projects/create-project/create-project.component';
import { ProjectDetailComponent } from './projects/project-detail/project-detail.component';
import { ProfileComponent } from './profile/profile.component';

export const routes: Routes = [
    { path: "", redirectTo: "dashboard", pathMatch: "full" },
    { path: 'dashboard', component: DashboardComponent, title: 'Dashboard | Project Manager' },
    { path: 'projects', component: ProjectsComponent, title: 'Projects | Project Manager' },
    { path: "users/:id", component: UpdateUserComponent, title: 'People | Edit' },
    { path: "users", component: UsersComponent , title: 'People | Project Manager'},
    { path: "projects/create", component: CreateProjectComponent, title: 'Projects | Create' },
    { path: "projects/:id", component: ProjectDetailComponent, title: 'Projects | Edit' },
    { path: "profile", component: ProfileComponent, title: 'Profile | Project Manager' }

];
