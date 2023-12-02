import * as awsx from '@pulumi/awsx';

export const stellarRepository = new awsx.ecr.Repository('stellar', {
  name: 'stellar',
});
