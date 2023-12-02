import * as awsx from '@pulumi/awsx';

export const backendRepository = new awsx.ecr.Repository('backend', {
  name: 'backend',
});
